-- MySQL dump 10.13  Distrib 5.6.24, for Win64 (x86_64)
--
-- Host: localhost    Database: ds
-- ------------------------------------------------------
-- Server version	5.6.26-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `ds_course`
--

DROP TABLE IF EXISTS `ds_course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_course` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `label` varchar(256) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_course`
--

LOCK TABLES `ds_course` WRITE;
/*!40000 ALTER TABLE `ds_course` DISABLE KEYS */;
INSERT INTO `ds_course` VALUES (1,'Aeronautics and Astronautics'),(2,'Anthropology'),(3,'Architecture'),(4,'Biological Engineering'),(5,'Biology'),(6,'Brain and Cognitive Sciences'),(7,'Chemical Engineering'),(8,'Chemistry'),(9,'Civil and Environmental Engineering'),(10,'Comparative Media Studies/Writing'),(11,'Computational and Systems Biology'),(12,'Earth, Atmospheric and Planetary Sciences'),(13,'Economics'),(14,'Electrical Engineering and Computer Science'),(15,'Engineering Systems Division'),(16,'Global Studies and Languages'),(17,'Health Sciences and Technology'),(18,'History'),(19,'Linguistics and Philosophy'),(20,'Literature'),(21,'Management'),(22,'Materials Science and Engineering'),(23,'Mathematics'),(24,'Mechanical Engineering'),(25,'Media Arts and Sciences'),(26,'Music and Theater Arts'),(27,'Nuclear Science and Engineering'),(28,'Physics'),(29,'Political Science'),(30,'Science, Technology, and Society'),(31,'Urban Studies and Planning'),(32,'Women\'s and Gender Studies');
/*!40000 ALTER TABLE `ds_course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ds_person`
--

DROP TABLE IF EXISTS `ds_person`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_person` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `age` int(2) DEFAULT NULL,
  `create_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_person`
--

LOCK TABLES `ds_person` WRITE;
/*!40000 ALTER TABLE `ds_person` DISABLE KEYS */;
INSERT INTO `ds_person` VALUES (1,'Ross Geller',NULL,'2015-12-08 08:33:38'),(2,'Rachel Green',NULL,'2015-12-08 08:33:38'),(3,'Phoebe Buffay',NULL,'2015-12-08 08:33:38'),(4,'Chandler Bing',NULL,'2015-12-08 08:33:38'),(5,'Joey Tribbiani',NULL,'2015-12-08 08:33:38'),(6,'Monica Geller',NULL,'2015-12-08 08:33:38');
/*!40000 ALTER TABLE `ds_person` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ds_ref_sociality`
--

DROP TABLE IF EXISTS `ds_ref_sociality`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_ref_sociality` (
  `student_id` int(11) NOT NULL,
  `friend_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_ref_sociality`
--

LOCK TABLES `ds_ref_sociality` WRITE;
/*!40000 ALTER TABLE `ds_ref_sociality` DISABLE KEYS */;
INSERT INTO `ds_ref_sociality` VALUES (1,2),(1,3),(1,1),(1,4),(2,5),(2,3),(2,2),(3,1),(3,2),(3,3),(3,6),(4,6),(5,4),(5,5),(5,3),(6,1),(6,4);
/*!40000 ALTER TABLE `ds_ref_sociality` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ds_ref_stucourse`
--

DROP TABLE IF EXISTS `ds_ref_stucourse`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ds_ref_stucourse` (
  `student_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ds_ref_stucourse`
--

LOCK TABLES `ds_ref_stucourse` WRITE;
/*!40000 ALTER TABLE `ds_ref_stucourse` DISABLE KEYS */;
INSERT INTO `ds_ref_stucourse` VALUES (1,2),(1,3),(1,1),(1,4),(2,5),(2,3),(2,2),(2,8),(3,1),(3,2),(3,3),(3,6),(4,6),(4,7),(4,8),(4,9),(5,4),(5,5),(5,3),(5,7),(6,1),(6,9),(6,8),(6,4);
/*!40000 ALTER TABLE `ds_ref_stucourse` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-12-08 17:07:38
