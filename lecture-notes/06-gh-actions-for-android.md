# 06: GitHub Actions for Android

## CI

A software practice that requires teams to commit code to a shared repository. Committing code more often detects errors sooner and reduces debugging time when finding the source of the errors. Also, frequent code updates make it easier to merge changes from different branches. It means you can spend more time writing code and less time debugging errors and resolving merge conflicts.

When you commit code, you can build and test the code to ensure that the commit does not introduce errors. Also, you can include code linters, security checks, code coverage, functional test, and other custom checks. **Note:** you will be required to do a few of these in the **Project** assessment.

Building and testing the code requires a server. You can build and test the code locally before pushing it to your repository, or you can use a CI server that checks for new commits in your repository. We are going to do the latter.

## GitHub Actions

CI using GH Actions offers various workflows to build the code in a repository and run the tests. Workflows can run on GH-hosted VMs or your own hosted machines.

You can configure your workflow to run when an event occurs, i.e., when new code is pushed to your repository. Also, you can schedule a workflow to run, i.e., every day at midday.

GH runs your tests and provides the results of each test in the pull request so you can see if the change has introduced errors. When all tests in your workflow pass, the changes are ready to be reviewed or merged. When a test fails, one of your changes may have caused the failure.

## Try GitHub Actions for Android

You will use GH Actions for instrumented testing/ UI testing with Espresso, generating an Android Package (APK), and code linting.

**Resources:** 
- https://docs.github.com/en/actions/guides/about-continuous-integration
- https://docs.github.com/en/actions/automating-your-workflow-with-github-actions/virtual-environments-for-github-hosted-runners
- https://docs.github.com/en/actions/automating-your-workflow-with-github-actions/about-self-hosted-runners
