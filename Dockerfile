FROM maven:3.8.4-openjdk-17
COPY . /usr/src/myapp
WORKDIR /usr/src/myapp
RUN curl -o allure-2.15.0.tgz -Ls https://github.com/allure-framework/allure2/releases/download/2.15.0/allure-2.15.0.tgz \
    && tar -zxvf allure-2.15.0.tgz -C /opt/ \
    && ln -s /opt/allure-2.15.0/bin/allure /usr/bin/allure \
    && rm allure-2.15.0.tgz
CMD mvn clean test && allure serve -h 0.0.0.0 -p 8080 target/allure-results