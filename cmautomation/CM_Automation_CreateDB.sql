DROP DATABASE  IF EXISTS CM_Automation;

CREATE DATABASE  CM_Automation;
USE CM_Automation;


DROP TABLE IF EXISTS QACheckList;
DROP TABLE IF EXISTS DeploymentCheckList;
DROP TABLE IF EXISTS EnvironmenWiseDeploymentDate;
DROP TABLE IF EXISTS deployement_defectlist;
DROP TABLE IF EXISTS DeploymentPlan;
DROP TABLE IF EXISTS Defect_Fix_Detail;
DROP TABLE IF EXISTS Deployment_Environments;
DROP TABLE IF EXISTS Vendor;
DROP TABLE IF EXISTS Applications;
DROP TABLE IF EXISTS CM_Users;
DROP TABLE IF EXISTS CM_Roles;



CREATE TABLE CM_Roles (
    role_Id int NOT NULL auto_increment,
	roleName varchar(255) NOT NULL,	
    CONSTRAINT PK_CM_Role_Id PRIMARY KEY (role_Id)
); 

CREATE TABLE CM_Users (
    user_Id int NOT NULL auto_increment,
	userName varchar(255) NOT NULL,
	designation varchar(255) NOT NULL,
	password varchar(9) NOT NULL,
	invalidLoginAttemt int,
    enabled int,
	role_Id int NOT NULL,
    CONSTRAINT PK_CM_User_Id PRIMARY KEY (user_Id),	 
    FOREIGN KEY (role_Id) REFERENCES CM_Roles(role_Id)
); 


CREATE TABLE Applications (
    application_Id int NOT NULL auto_increment,
	applicationName varchar(255) NOT NULL,	
    description varchar(500) NULL,	
	CONSTRAINT PK_Application_Id PRIMARY KEY (application_Id)
); 


CREATE TABLE Vendor (
    vendor_Id int NOT NULL auto_increment,
	vendorName varchar(255) NOT NULL,	
	description varchar(500) NULL,	
	CONSTRAINT PK_Vendor_Id PRIMARY KEY (vendor_Id)
); 


CREATE TABLE Deployment_Environments (
    environment_Id int NOT NULL auto_increment,
	environmentName varchar(255) NOT NULL,	
	description varchar(500) NULL,	
	CONSTRAINT PK_Environment_Id PRIMARY KEY (environment_Id)
);

 

 CREATE TABLE Defect_Fix_Detail (
    defect_Id int NOT NULL auto_increment,
	title varchar(255) NOT NULL,
	application_Id int NOT NULL,
	vendor_Id  int NOT NULL,
	defectCreationDate date NOT NULL,
	description varchar(500) NOT NULL,
	dependentDefect_Id int NULL,
	status int NOT NULL  CHECK (status in (1,2,3,4,5,6,7,8,9)),/*1=WaitingForFix, 2=FixReceived, 3=DeployedInSDF, 4=DeployedInIST1, 5=DeployedInIST2,6=DeployedInPROD, 7=DeployedInTraining, 8=DeployedInDR, 9=Closed*/
	fixRecieveDate date NOT NULL,
	impactedComponent varchar(500) NOT NULL,	
	deploymentPackageLocation varchar(500) NOT NULL,
	isTestCaseProvided int NOT NULL CHECK (isTestCaseProvided=1 or isTestCaseProvided=2),
	isDeploymentInstructionProvided int NOT NULL CHECK (isDeploymentInstructionProvided=1 or isDeploymentInstructionProvided=2),
    
	reviewDate date NOT NULL,
    CONSTRAINT PK_Defect_Id PRIMARY KEY (defect_Id),	 
    FOREIGN KEY (application_Id) REFERENCES Applications(application_Id),
	FOREIGN KEY (vendor_Id) REFERENCES Vendor(vendor_Id),
	FOREIGN KEY (dependentDefect_Id) REFERENCES Defect_Fix_Detail(defect_Id)
); 


CREATE TABLE `deploymentplan` (
  `deployment_Id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `application_Id` int(11) NOT NULL,
  `planDate` date NOT NULL,
  `dev_DeploymentDate` date DEFAULT NULL,
  `sdf_DeploymentDate` date DEFAULT NULL,
  `ist1_DeploymentDate` date DEFAULT NULL,
  `ist2_DeploymentDate` date DEFAULT NULL,
  `prod_DeploymentDate` date DEFAULT NULL,
  `comment` varchar(500) NOT NULL,
  PRIMARY KEY (`deployment_Id`),
  KEY `application_Id` (`application_Id`),
  CONSTRAINT `deploymentplan_ibfk_1` FOREIGN KEY (`application_Id`) REFERENCES `applications` (`application_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `deployement_defectlist` (
  `deployement_Id` int(11) NOT NULL,
  `defect_Id` int(11) NOT NULL,
  PRIMARY KEY (`deployement_Id`,`defect_Id`),
  KEY `defect_Id_idx` (`defect_Id`),
  CONSTRAINT `defect_Id` FOREIGN KEY (`defect_Id`) REFERENCES `defect_fix_detail` (`defect_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `deployment_Id` FOREIGN KEY (`deployement_Id`) REFERENCES `deploymentplan` (`deployment_Id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


 CREATE TABLE DeploymentCheckList (
	deploymentChecklist_Id int not null auto_increment,
	deployment_Id  int NOT NULL,
	environment_Id int NOT NULL,		
	actualDeploymentDate date NOT NULL,
	isPackageDeployed char NOT NULL CHECK (isPackageDeployed='y' or isPackageDeployed='n'),
	deployedPackageInfo varchar(500) NOT NULL,
	isParameterDeployed char NOT NULL CHECK (isParameterDeployed='y' or isParameterDeployed='n'),
	deployedParameterInfo varchar(500) NOT NULL,
	isDBScriptExecuted char NOT NULL CHECK (isDBScriptExecuted='y' or isDBScriptExecuted='n'),
	dBScriptInfo varchar(500) NOT NULL,
	otherDeploymentInfo varchar(500) NOT NULL,
	deployedBy varchar(255) NOT NULL,
    CONSTRAINT PK_DeploymentCheckList PRIMARY KEY (deploymentChecklist_Id),	 
    FOREIGN KEY (deployment_Id) REFERENCES DeploymentPlan(deployment_Id),
	FOREIGN KEY (environment_Id) REFERENCES Deployment_Environments(environment_Id),
    UNIQUE KEY `environment_Id_env`(`deployment_Id`,`environment_Id`)
	/*FOREIGN KEY (deployedBy) REFERENCES CM_Users(user_Id)*/
);

 CREATE TABLE QACheckList (
	qachecklist_id int NOT NULL auto_increment,
	deployment_Id  int NOT NULL,
	environment_Id int NOT NULL,		
	testDate date NOT NULL,
	testStatus int NOT NULL,
	comment varchar(500) NOT NULL,	
	testedBy varchar(255) NOT NULL,
    CONSTRAINT PK_QACheckList PRIMARY KEY (qachecklist_id),	 
    FOREIGN KEY (deployment_Id) REFERENCES DeploymentPlan(deployment_Id),
	FOREIGN KEY (environment_Id) REFERENCES Deployment_Environments(environment_Id),
    UNIQUE KEY `deployment_Id_env` (`deployment_Id`,`environment_Id`)
	/*FOREIGN KEY (testedBy) REFERENCES CM_Users(user_Id)			*/
);



/*insert data into application*/
INSERT INTO `cm_automation`.`applications`
(`application_Id`,`applicationName`,description) VALUES (1,'App1', 'Application for Teller');

INSERT INTO `cm_automation`.`applications`
(`application_Id`,`applicationName`,description) VALUES (2,'App2', 'Checque processing application');

INSERT INTO `cm_automation`.`applications`
(`application_Id`,`applicationName`,description) VALUES (3,'App3', 'Currency validation application');


/*insert data into vendor*/

INSERT INTO `cm_automation`.`vendor`
(`vendor_Id`,`vendorName`,description) VALUES (1, 'IBM', 'Providing multiple application');

INSERT INTO `cm_automation`.`vendor`
(`vendor_Id`,`vendorName`,description) VALUES (2, 'NCR', 'Providing multiple application');


/* insert data into deployment_environments */
INSERT INTO `deployment_environments` (`environment_Id`,`environmentName`,`description`) VALUES (1,'DEV','For developers');
INSERT INTO `deployment_environments` (`environment_Id`,`environmentName`,`description`) VALUES (2,'SDf','System Development Facility');
INSERT INTO `deployment_environments` (`environment_Id`,`environmentName`,`description`) VALUES (3,'IST1','Integrated System Testing 1');
INSERT INTO `deployment_environments` (`environment_Id`,`environmentName`,`description`) VALUES (4,'IST2','Integrated System Testing 2');
INSERT INTO `deployment_environments` (`environment_Id`,`environmentName`,`description`) VALUES (5,'PROD','Production Environment');


/*insert data into defect fix detail*/
INSERT INTO `cm_automation`.`defect_fix_detail`
(`defect_Id`,`title`,`application_Id`,`vendor_Id`,`defectCreationDate`,`description`,
`dependentDefect_Id`,`status`,`fixRecieveDate`,`impactedComponent`,`deploymentPackageLocation`,
`isTestCaseProvided`,`isDeploymentInstructionProvided`,`reviewDate`)
VALUES
(5, 'Currency conversion error', 1, 1, (SELECT CURDATE() - 1),'Currency conversion is not proper for less than $1',
null, 2, (SELECT CURDATE()-1), 'N/A', 'Data Server', 1, 1, (SELECT CURDATE()));


INSERT INTO `cm_automation`.`defect_fix_detail`
(`defect_Id`,`title`,`application_Id`,`vendor_Id`,`defectCreationDate`,`description`,
`dependentDefect_Id`,`status`,`fixRecieveDate`,`impactedComponent`,`deploymentPackageLocation`,
`isTestCaseProvided`,`isDeploymentInstructionProvided`,`reviewDate`)
VALUES
(2, 'Wrong message to user', 2, 2, (SELECT CURDATE() - 1),'Showing wrong message to customer for account to account transfer',
null, 2, (SELECT CURDATE()-1), 'N/A', 'Data Server', 1, 1, (SELECT CURDATE() ));

INSERT INTO `cm_automation`.`defect_fix_detail`
(`defect_Id`,`title`,`application_Id`,`vendor_Id`,`defectCreationDate`,`description`,
`dependentDefect_Id`,`status`,`fixRecieveDate`,`impactedComponent`,`deploymentPackageLocation`,
`isTestCaseProvided`,`isDeploymentInstructionProvided`,`reviewDate`)
VALUES
(3, 'Duplicate entry created for online transaction', 1, 1, (SELECT CURDATE() - 1),'Duplicate entry created for online transaction only',
null, 2, (SELECT CURDATE()-1), 'N/A', 'Data Server', 1, 1, (SELECT CURDATE()));

INSERT INTO `cm_automation`.`defect_fix_detail`
(`defect_Id`,`title`,`application_Id`,`vendor_Id`,`defectCreationDate`,`description`,
`dependentDefect_Id`,`status`,`fixRecieveDate`,`impactedComponent`,`deploymentPackageLocation`,
`isTestCaseProvided`,`isDeploymentInstructionProvided`,`reviewDate`)
VALUES
(4, 'Summery file not generated', 1, 1, (SELECT CURDATE() - 1),'Summery file not generated for over 10000 transactions',
null, 2, (SELECT CURDATE()-1), 'N/A', 'Data Server', 1, 1, (SELECT CURDATE()));


/*insert into deployment plan*/

INSERT INTO `deploymentplan` (`deployment_Id`,`title`,`application_Id`,`planDate`,`dev_DeploymentDate`,`sdf_DeploymentDate`,`ist1_DeploymentDate`,`ist2_DeploymentDate`,`prod_DeploymentDate`,`comment`) VALUES (1,'de1',1,'2018-03-02','2018-03-02','2018-03-02','2018-03-02','2018-03-02','2018-03-02','c1');
INSERT INTO `deploymentplan` (`deployment_Id`,`title`,`application_Id`,`planDate`,`dev_DeploymentDate`,`sdf_DeploymentDate`,`ist1_DeploymentDate`,`ist2_DeploymentDate`,`prod_DeploymentDate`,`comment`) VALUES (2,'de2',2,'2018-03-02','2018-03-02','2018-03-02','2018-03-02','2018-03-02','2018-03-02','c2');
commit;
