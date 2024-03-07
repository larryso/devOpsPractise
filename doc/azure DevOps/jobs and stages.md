# Jobs and Stages
You can orgnize pipeline jobs into stages, stages are the major divisions in a pipeline.
Every pipeline has at least one stage even if you don't explicitly define it. You can also arrage stages into a dependency graph so that one stage runs before another one. 

There is a limit of 256 jobs for a stage

```yml
## this pipeline has one implicit stage
pool:
  vmImage: 'ubuntu-latest'
jobs:
  - job: A
    steps:
      - bash: echo "A"
  - job: B
    steps:
      - bash: echo "B"
    
```
If you orgize your pipeline into multiple stages, you use stages keyword

```yml
stages:
  - stage: A
    pool: StageAPool
    jobs:
      - job: A1
      - job: A2
        pool: jobPool

- stage: B
    jobs:
      - job: B1
      - job: B2
       
```
If you choose to specific a pool at the stage level, then all jobs defined in that stage use that pool unless specified at the job-level

You can define conditions and dependencies for each stage. [more reference -> here](https://learn.microsoft.com/en-us/azure/devops/pipelines/process/stages?view=azure-devops&tabs=yaml)

## Deployment jobs
In YAML pipelines, we recommend that you put your deployment steps in a special type of job called a deployment job. A deployment job is a collection of steps that are run sequentially against the environment. A deployment job and a traditional job can exist in the same stage. 