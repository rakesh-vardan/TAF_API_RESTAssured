pipeline {
    agent any  // Run on any available agent

    tools {
        maven "Maven" // Install the Maven version configured as "Maven" and add it to the path.
    }

    environment {
        PROJECT_DIR = "${workspace}" // Adjust for Windows file paths
    }

    stages {
        stage('Checkout') {
            steps {
                bat 'figlet "CHECKOUT"'
                // Checkout the Git repository to the workspace
                git url: 'https://github.com/rakesh-vardan/TAF_API_RESTAssured.git', branch: 'main'
            }
        }

        stage('Verify Directory Structure') {
            steps {
                // Debugging: List files in the workspace to check if pom.xml exists
                bat 'figlet "LIST FILES"'
                script {
                    echo "Listing files in the project directory: ${PROJECT_DIR}"
                    bat "dir ${PROJECT_DIR}" // Windows command to list directory contents
                }
            }
        }

        stage('Install Dependencies') {
            steps {
                // Run mvn clean install from the root directory of the project
                bat 'figlet "INSTALL DEPENDENCIES"'

                dir("${PROJECT_DIR}") {
                    script {
                        echo "Running mvn clean install"
                        bat "mvn clean install -DskipTests"  // Run Maven command on Windows
                    }
                }
            }
        }

        stage('Run API Tests') {
            steps {
                // Run the REST Assured API tests
                bat 'figlet "RUN API TESTS"'

                dir("${PROJECT_DIR}") {
                    script {
                        echo "Running mvn test"
                        bat "mvn test"  // Run Maven test on Windows
                    }
                }
            }
        }
    }

    post {
        always {
            // Publish the Allure report (ensure correct path)
            allure includeProperties: false, jdk: '', results: [[path: 'target/allure-results']]
            // Clean workspace after build
            cleanWs()
        }

        success {
            echo "Tests and reports were successfully generated!"
        }

        failure {
            echo "Tests failed. Please check the logs for details."
        }
    }
}