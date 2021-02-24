import jetbrains.buildServer.configs.kotlin.v2019_2.*
import jetbrains.buildServer.configs.kotlin.v2019_2.buildSteps.maven
import jetbrains.buildServer.configs.kotlin.v2019_2.triggers.vcs

/*
The settings script is an entry point for defining a TeamCity
project hierarchy. The script should contain a single call to the
project() function with a Project instance or an init function as
an argument.

VcsRoots, BuildTypes, Templates, and subprojects can be
registered inside the project using the vcsRoot(), buildType(),
template(), and subProject() methods respectively.

To debug settings scripts in command-line, run the

    mvnDebug org.jetbrains.teamcity:teamcity-configs-maven-plugin:generate

command and attach your debugger to the port 8000.

To debug in IntelliJ Idea, open the 'Maven Projects' tool window (View
-> Tool Windows -> Maven Projects), find the generate task node
(Plugins -> teamcity-configs -> teamcity-configs:generate), the
'Debug' option is available in the context menu for the task.
*/

version = "2020.2"

project {

    buildType(Build)
}

object Build : BuildType({
    name = "Build"

    vcs {
        root(DslContext.settingsRoot)
    }

    steps {
        maven {
            id = "RUNNER_1"
            goals = "clean test"
            runnerArgs = "-Dmaven.test.failure.ignore=true"
        }
        step {
            id = "RUNNER_2"
            type = "teamcity-veracode-plugin"
            param("vid", "8f41193670308f0c0973574cc63e8a23")
            param("teams", "Default Team")
            param("appName", "Verademo-Policy Scan")
            param("createProfile", "false")
            param("criticality", "High")
            param("waitForScan", "false")
            param("useGlobalCredentials", "false")
            param("createSandbox", "true")
            param("version", "%env.BUILD_NUMBER%")
            param("vkey", "eb466c7561e1d434469287b3f183489f2615435741fc75f84220df1e34bac4edf179cf672d6c4733c89030c1e304d7fca4bf01608c280bc6d4c8cfcfe7523239")
        }
        stepsOrder = arrayListOf("RUNNER_1", "RUNNER_2")
    }

    triggers {
        vcs {
        }
    }
})
