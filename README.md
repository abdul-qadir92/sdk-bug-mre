# Minimal Reproducible Example: SDK 1.43.0 `TmpSuiteListener` Bug

This project provides the minimal configuration to reproduce the `SurefireBooterForkException` caused by the BrowserStack SDK (v1.43.0) when using a Maven profile that does not include the SDK on its classpath.

## Prerequisites

* Java 17

* Apache Maven

## 1. How to Reproduce the Failure

Run the build using the `local` profile, which does **not** configure the BrowserStack SDK java agent.

```
# Step 1: Clean and install dependencies
# This ensures all plugins are downloaded, including the SDK 1.43.0
mvn clean install

# Step 2: Run tests using the 'local' profile
# This command will fail.
mvn test -P local


```

### Expected (Failure) Output

You will see the build fail with the errors reported in the bug:

```
[INFO] --- surefire:3.5.4:test (default-cli) @ sdk-bug-mre ---
[INFO] Using auto-detected provider org.apache.maven.surefire.testng.TestNGProvider
...
[ERROR] org.testng.ITestNGListener: Provider com.browserstack.testNgListeners.TmpSuiteListener could not be instantiated
...
[ERROR] org.apache.maven.surefire.booter.SurefireBooterForkException: There was an error in the forked process
...
[INFO] ------------------------------------------------------------------------
[INFO] BUILD FAILURE
[INFO] ------------------------------------------------------------------------


```

## 2. How to Show the "Working" (BrowserStack) Build

If you run the build using the `browserstack` profile, it will work. This proves that the basic setup is correct.

```
# This command will pass (assuming no BS_USERNAME/BS_ACCESS_KEY are set,
# it will just fail the SDK auth, but the test itself will *run*).
mvn test -P browserstack


```

## 3. How to Demonstrate the Workaround

To prove this is a regression in `1.43.0`, edit the `pom.xml` file:

1. **Change** the `<browserstack.sdk.version>` property:

   *From:*

   ```
   <browserstack.sdk.version>1.43.0</browserstack.sdk.version>
   
   
   ```

   *To:*

   ```
   <browserstack.sdk.version>1.42.4</browserstack.sdk.version>
   
   
   ```

2. Re-run the **failing command** from Step 1:

   ```
   # Re-run the same 'local' profile test
   mvn test -P local
   
   
   ```
