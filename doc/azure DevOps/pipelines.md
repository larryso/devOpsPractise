# Azure Pipelines
![](../../img/azure%20Devops.webp)

A pipeline is one or more stages that describe a CI/CD process. Stages are the major divisions in a pipeline. The stages "Build this app," "Run these tests," and "Deploy to preproduction" are good examples.

A stage is one or more jobs, which are units of work assignable to the same machine. You can arrange both stages and jobs into dependency graphs. Examples include "Run this stage before that one" and "This job depends on the output of that job."

A job is a linear series of steps. Steps can be tasks, scripts, or references to external templates.

Examples:

```yaml
trigger:
- main

pool: 
  vmImage: ubuntu-latest

stages:
- stage: CI
  jobs:
  - job: CIWork
    steps:
    - script: "Do CI work"

- stage: Test
  jobs:
  - job: TestWork
    steps:
    - script: "Do test work"
```

## Reference
[https://learn.microsoft.com/en-us/azure/devops/pipelines/?view=azure-devops](https://learn.microsoft.com/en-us/azure/devops/pipelines/?view=azure-devops)