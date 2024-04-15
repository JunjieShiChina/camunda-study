package me.shijunjiee.camundastudy;

import jakarta.annotation.Resource;
import org.camunda.bpm.engine.HistoryService;
import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.history.HistoricActivityInstance;
import org.camunda.bpm.engine.repository.Deployment;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.Execution;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = CamundaStudyApplication.class)
public class RuntimeServiceTest {

    @Resource
    private RepositoryService repositoryService;

    @Resource
    private RuntimeService runtimeService;

    @Resource
    private TaskService taskService;

    @Resource
    private HistoryService historyService;

    @Test
    public void testDeployAndRun() {

        String deployName = "部署名称";
        String defName = "指定任务测试1";

        // deploy
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("auditpersontest1.bpmn").name(deployName)
                .deploy();

        // 查询流程定义
        ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().processDefinitionName(defName).latestVersion().list().get(0);

        // start process
        ProcessInstance process_0wtwg02 = runtimeService.startProcessInstanceById(processDefinition.getId());

        // queryTask
        List<Task> demo = taskService.createTaskQuery().taskAssignee("demo").list();

        // completeTask
        taskService.complete(demo.get(0).getId());
        
        System.out.println(demo);

    }

    @Test
    public void testDeploy() {

        String deployName = "部署名称";
        String defName = "指定任务测试1";

        // deploy
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("diagram_1_manyperson.bpmn").name(deployName)
                .deploy();
    }

    @Test
    public void testGetProcessDefinition() {
        List<ProcessDefinition> list = repositoryService.createProcessDefinitionQuery().processDefinitionKey("Process_topic_circle").list();
        System.out.println(list);
    }

    // 流程实例查询测试
    @Test
    public void testQueryProcessInstance() {
        List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().processDefinitionKey("Process_inclusive_test").list();
        System.out.println(list);
        ProcessInstance processInstance = list.get(0);
        // 查询该流程实例当前节点
        List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstance.getId());
        System.out.println(activeActivityIds);

        // 查询流程实例完成的节点
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId(processInstance.getId())
                .list();

        System.out.println(historicActivityInstances);

        // 查询流程实例的执行实例
        List<Execution> executions = runtimeService.createExecutionQuery()
                .processInstanceId(processInstance.getId())
                .list();

        // 输出执行实例信息
        System.out.println(executions);


    }

}
