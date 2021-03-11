#!/usr/bin/env bash
sudo yum update
echo "Installing Git.."
sudo yum install -y git



echo "Installing Java 8.."

sudo yum install -y java-1.8.0-openjdk-devel


echo "Installing Maven.."
sudo yum install -y wget

wget  http://www.eu.apache.org/dist/maven/maven-3/3.3.9/binaries/apache-maven-3.3.9-bin.tar.gz
tar xzf apache-maven-3.3.9-bin.tar.gz
mkdir /usr/local/maven
mv apache-maven-3.3.9/ /usr/local/maven/
alternatives --install /usr/bin/mvn mvn /usr/local/maven/apache-maven-3.3.9/bin/mvn 1
alternatives --config mvn

git clone https://github.com/pratikkanungo/spring-with-react.git
cd spring-with-react/core
mvn spring-boot:run