To run application locally:

**Register for an Amazon Affiliates account:**

https://docs.aws.amazon.com/AWSECommerceService/latest/GSG/GettingStarted.html

Fill in the following values in /bookbash-api-core/src/main/resources/api.yaml:

    ASSOCIATE_TAG: <FILL_IN_HERE>
    ACCESS_KEY_ID: <FILL_IN_HERE>
    SECRET_KEY: <FILL_IN_HERE>

**Creating the database:**

Install Postgres server locally
Create database named "book_bash"
Create a super user with login role named:

    USERNAME: book_bash_db_user
    PASSWORD: PbNq7ZUBGRA3yGV

**Populating the database**

With the database and user created, run the following Unit test in the "excluded-tests" project:

    org.melusky.bookbash.service.util.impl.DatabaseCreationProcessImplTest.test_create_database

**Running the API server locally**

To run application locally, run gradle task "bootRun" in the "book-bash-api-core" module.  This will launch an application locally on http://localhost:8080 which you can make requests to.

**Deploying the API server to Tomcat**

Run the "war" task in the same module above to produce a war file in the "build" directory of the "api core" project.  Deploy this war into tomcat using manager or drop in "webapps" directory.  Set Java property _-D.spring.profiles.active_ to the profile defined in api-tomcat.yaml.

**Deploying the API server as a JAR file**

Run the "bootRepackage" gradle task in the same project.  This should produce a Java "fat jar" around 60MB in size.  Take the JAR that's produced in the "build" directory.  Rename the JAR file bookbash.jar.  Run the folloing to launch the server on port 8080:

    java -jar bookbash.jar