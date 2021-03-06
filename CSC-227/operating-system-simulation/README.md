# Operating System Simulation

App to simulate how Operating System works

## Project Description

#### Hardware

The computer hardware is assumed to have:

-   A hard disk of size of 10 GB where 20% of this size is used to store the user programs.
-   A RAM of size 192MB, where 32MB is used to store the OS.
-   A CPU that executes one instruction each unit of time.
-   An IO device for input and output operations.
-   An internal clock that ticks every unit of time.

#### Operating System

The operating system is assumed to have:

-   Job Scheduling: The program with the smallest size is first selected to be loaded in the main memory. We call this
    technique by SSPF.
-   CPU scheduling: The CPU is allocated to the program with the smallest expected running time. We call this technique
    by SETF.

#### Program Specifications

Each program has:

-   ID
-   Program State: New, Ready, Waiting, Running, TerminatedNormally, or TerminatedAbnormally
-   Program Size: distributed between 16KB and 16384KB
-   Expected Execution Time: distributed between 16ut and 512ut and grater or equal to Exact Execution
-   Exact Execution Time: distributed between 16ut and 512ut
-   The expected IO Time: distributed between 100ut and 200ut

#### The Machine Execution Cycle

The following algorithm simulates the machine Execution Cycle:

```text
While true do {
    Increments the simulated clock by one unit of time
    (* This assumes that one instruction is executed *)
    If there are interrupts
        Then Interrupts the current program and calls the ISRi
    endif
}
```

Interrupts are also randomly generated:

-   The possibility that there are interrupts is 10%
-   The possibility that there is an IO request is 20%
-   The possibility that the busy IO device will terminate is 20%
-   The possibility that the program terminates normally is 5%
-   The possibility that the program terminates abnormally is 1%

The main simulator program is like this:

```text
While there are jobs in the H-Disk do {
    Run the Machine Execution Cycle
}
Print the required statistics
```

#### The Expected Output From The Simulation

At the end of the simulation, the program print the following results:

-   The number of initially generated jobs stored on the H-disk.
-   The average program size of all jobs.
-   The average number of jobs that have completed their execution normally.
-   The average number of jobs that have completed their execution abnormally.
-   The number of CPU bound jobs.

## Requirements

-   [Java](https://www.oracle.com/java/technologies/downloads)

## Installation

```shell
$ git clone https://github.com/devmoath/university-projects.git
```

## Usage

```shell
$ cd university-projects/CSC-227/operating-system-simulation/src
$ javac MEC.java
$ java MEC
```

![screenshot](img/screenshot.png)
