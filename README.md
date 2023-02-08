# Spring Batch

## What is Batch Processing
- Batch processing is a processing mode which involves execution of series of automated complex jobs without user interaction. A batch process handles bulk data and runs for a long time.

## What is Spring Batch
- Spring Batch follows the traditional batch architecture where a job repository does the work of scheduling and interacting with the job.

- A job can have more than one step. And every step typically follows the sequence of reading data, processing it and writing it.

- And of course the framework will do most of the heavy lifting for us here — especially when it comes to the low-level persistence work of dealing with the jobs

## Components of Spring Batch
- Job
    - In a Spring Batch application, a job is the batch process that is to be executed. It runs from start to finish without interruption. This job is further divided into steps

- Step
    - A step is an independent part of a job which contains the necessary information to define and execute the job (its part).

    - Each step is composed of an ItemReader, ItemProcessor (optional) and an ItemWriter. A job may contain one or more steps.   

- Item Reader
    - An item reader reads data into a Spring Batch application from a particular source.

- Item Processor
    - An Item processor is a class which contains the processing code which processes the data read into the spring batch. If the application reads "n" records, then the code in the processor will be executed on each record.

- Item Writer
    - An item writer writes data from the Spring Batch application to a particular destination.

- Job Launcher
    - JobLauncher is an interface which launces the Spring Batch job with the given set of parameters. SampleJoblauncher is the class which implements the JobLauncher interface.

- Job Instance
    - A JobInstance represents the logical run of a job; it is created when we run a job. Each job instance is differentiated by the name of the job and the parameters passed to it while running.

## What does this code do
- It reads user data from a csv and save the same in DB
- It has a basic processing logic to understand how ItemProcessor works.

## API to trigger the batch
- `http://localhost:9091/trigger` - Trigger the Spring Batch 
- `http://localhost:9091/h2-console` - H2 Console for querying the in-memory tables.

## H2 Config
- Database: `testdb`
- User: `sa`
- Password: 
