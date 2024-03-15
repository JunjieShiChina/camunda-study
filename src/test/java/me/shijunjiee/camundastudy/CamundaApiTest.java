package me.shijunjiee.camundastudy;

import org.camunda.bpm.engine.*;
import org.camunda.bpm.engine.history.HistoricProcessInstanceQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CamundaStudyApplication.class)
public class CamundaApiTest {

    @Test
    public void testApi() {
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 负责流程定义与部署
        RepositoryService repositoryService = processEngine.getRepositoryService();
        // 部署
        repositoryService.createDeployment().addClasspathResource("").deploy();
        // 删除...
        repositoryService.deleteDeployment("");

        // 负责流程实例的相关操作，包含流程变量等
        RuntimeService runtimeService = processEngine.getRuntimeService();
        runtimeService.startProcessInstanceByMessage("");
        runtimeService.deleteProcessInstance("", "");

        // 负责具体业务的内部操作，也可以操作变量
        TaskService taskService = processEngine.getTaskService();
        taskService.saveTask(null);
        taskService.complete("");
        taskService.deleteTask("");
        taskService.newTask();

        /**
         * setAssignee或者claim后不能再被claim。但是delegateTask后是可以再claim的
         */
        // 领取任务
        taskService.claim("", "");
        // 委派任务负责人
        taskService.delegateTask("", "");
        // 指定任务负责人
        taskService.setAssignee("", "");

        // 负责所有流程引擎产生的历史数据，执行的记录等数据。数据量大，可以配置数据记录的级别
        HistoryService historyService = processEngine.getHistoryService();
        HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
        historicProcessInstanceQuery.processInstanceBusinessKey("");

        // 负责用户和组的操作
        IdentityService identityService = processEngine.getIdentityService();
        // 负责数据表job，计时器等数据的操作，业务中一般永不到
        ManagementService managementService = processEngine.getManagementService();
        // 负责表单相关操作
        FormService formService = processEngine.getFormService();
    }

}
