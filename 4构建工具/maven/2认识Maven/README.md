# 2认识Maven

    1认识maven
    2安装maven
    3配置maven

## 1认识maven

maven用于java项目的构建，打包，发布，部署等等

官网地址：https://maven.apache.org/index.html

## 2安装maven

下载`apache-maven-3.6.1-bin.zip`，并解压到`E:\6_Java\3_ArcheTool\maven`目录

配置环境变量：

```
MAVEN_HOME=E:\6_Java\3_ArcheTool\maven\apache-maven-3.6.1
PATH变量中添加 %MAVEN_HOME%\bin
```

在命令行终端验证
```
C:\Users\Administrator>mvn -version
Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T03:00:29+08:00)
Maven home: E:\6_Java\3_ArcheTool\maven\apache-maven-3.6.1\bin\..
Java version: 1.8.0_91, vendor: Oracle Corporation, runtime: E:\6_Java\1_JDK\jdk1.8.0_91\jre
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```

说明已经安装好

## 3配置maven

配置maven开发环境
将官方setting.xml 拷贝setting_defualt.xml

用下面配置覆盖
```xml
<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 http://maven.apache.org/xsd/settings-1.0.0.xsd">
    <!-- 本地仓库地址 -->
  <localRepository>E:\6_Java\3_ArcheTool\maven\mavenRepo</localRepository>
    <!-- 插件组 -->
  <pluginGroups>
    <pluginGroup>com.spotify</pluginGroup>
  </pluginGroups>

  <proxies></proxies>

    <!-- 服务器列表，配置私服服务器，及web容器 -->
  <servers>
  	<server>
  	  <id>daphne_releases</id>
  	  <username>admin</username>
  	  <password>admin123</password>
	  </server>
    <server>
      <id>nexus-releases</id>
      <username>zhenyun.su</username>
      <password>daphne</password>
    </server>
    <server>
      <id>nexus-snapshots</id>
      <username>zhenyun.su</username>
      <password>daphne</password>
    </server>

    <server>
      <id>tomcatloc</id>
      <username>admin</username>
      <password>admin</password>
    </server>

    <server>
      <id>tomcatdev</id>
      <username>admin</username>
      <password>daphne</password>
    </server>

    <server>
      <id>tomcatsit</id>
      <username>admin</username>
      <password>daphne</password>
    </server>

    <server>
      <id>tomcatprd</id>
      <username>adminszy</username>
      <password>daphne+Szy</password>
    </server>

    <server>
      <id>tomcat-wexin-dev</id>
      <username>admin</username>
      <password>daphne</password>
    </server>
  </servers>

  <mirrors>
  </mirrors>

      <!-- 配置多环境 -->
  <profiles>
        <!-- 达芙妮私服 -->
  	  <profile>
	    <id>nexus-daphne</id>
	    <repositories>
	      <repository>
	        <id>nexus-releases</id>
	        <url>http://192.168.*.47:8081/nexus/content/groups/public</url>
	        <releases>
	          <enabled>true</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>
	      </repository>
	      <repository>
	        <id>nexus-snapshots</id>
	        <url>http://192.168.*.47:8081/nexus/content/groups/public-snapshots</url>
	        <releases>
	          <enabled>true</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>
	      </repository>
	    </repositories>
	    <pluginRepositories>
	      <pluginRepository>
	        <id>nexus-releases</id>
	        <url>http://192.168.*.47:8081/nexus/content/groups/public</url>
	        <releases>
	          <enabled>true</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>
	      </pluginRepository>
	      <pluginRepository>
	        <id>nexus-snapshots</id>
	        <url>http://192.168.*.47:8081/nexus/content/groups/public-snapshots</url>
	        <releases>
	          <enabled>true</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>
	      </pluginRepository>
	    </pluginRepositories>
	  </profile>
      <!-- 阿里云仓库 -->
	  <profile>
		  <id>nexus-aliyun</id>
		  <repositories>
			  <repository>
				  <id>aliyun</id>
				  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
				  <releases>
					  <enabled>true</enabled>
				  </releases>
				  <snapshots>
					  <enabled>true</enabled>
				  </snapshots>
			  </repository>
		  </repositories>
		  <pluginRepositories>
			  <pluginRepository>
				  <id>aliyun</id>
				  <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
				  <releases>
					  <enabled>true</enabled>
				  </releases>
				  <snapshots>
					  <enabled>true</enabled>
				  </snapshots>
			  </pluginRepository>
		  </pluginRepositories>
	  </profile>
      <!-- oschina仓库 -->
	  <profile>
		  <id>nexus-oschina</id>
		  <repositories>
			  <repository>
				  <id>oschina</id>
				  <url>http://maven.oschina.net/content/groups/public/</url>
				  <releases>
					  <enabled>true</enabled>
				  </releases>
				  <snapshots>
					  <enabled>true</enabled>
				  </snapshots>
			  </repository>
		  </repositories>
		  <pluginRepositories>
			  <pluginRepository>
				  <id>oschina</id>
				  <url>http://maven.oschina.net/content/groups/public/</url>
				  <releases>
					  <enabled>true</enabled>
				  </releases>
				  <snapshots>
					  <enabled>true</enabled>
				  </snapshots>
			  </pluginRepository>
		  </pluginRepositories>
    </profile>
      <!-- maven中央仓库 -->
	  <profile>
	    <id>nexus-central</id>
	    <repositories>
	      <repository>
	        <id>central</id>
	        <url>https://repo1.maven.org/maven2/</url>
	        <releases>
	          <enabled>true</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>
	      </repository>
	    </repositories>
	    <pluginRepositories>
	      <pluginRepository>
	        <id>central</id>
	        <url>https://repo1.maven.org/maven2/</url>
	        <releases>
	          <enabled>true</enabled>
	        </releases>
	        <snapshots>
	          <enabled>true</enabled>
	        </snapshots>
	      </pluginRepository>
	    </pluginRepositories>
	  </profile>
  </profiles>


  <!-- activeProfiles
    <activeProfile>nexus-central</activeProfile>
    <activeProfile>nexus-oschina</activeProfile>
    <activeProfile>nexus-aliyun</activeProfile>
    <activeProfile>nexus-daphne</activeProfile>
  -->
  <activeProfiles>
    <activeProfile>nexus-daphne</activeProfile>
    <activeProfile>nexus-aliyun</activeProfile>
  </activeProfiles>

</settings>
```

该配置可以做为模板使用，更多配置说明，请参考默认setting.xml配置注释说明
