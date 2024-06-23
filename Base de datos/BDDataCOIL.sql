CREATE DATABASE  IF NOT EXISTS `coil_vic` /*!40100 DEFAULT CHARACTER SET utf8mb3 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `coil_vic`;
-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: coil_vic
-- ------------------------------------------------------
-- Server version	8.1.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `actividad`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `título` varchar(256) DEFAULT NULL,
  `descripcion` varchar(200) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `semana` varchar(45) NOT NULL,
  PRIMARY KEY (`idActividad`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `actividad`
--

LOCK TABLES `actividad` WRITE;
/*!40000 ALTER TABLE `actividad` DISABLE KEYS */;
INSERT INTO `actividad` VALUES (12,'Nueva actividad','Nueva descripcion','Reflexión','2'),(13,'Nueva actividaddddd','Nueva descripcionnnnnn','Proyecto COIL','2'),(14,'nueva actividad padrisima','Actividad nueva','Rompe hielo','1'),(15,'wows con','Wows con las actividades','Proyecto COIL','2'),(16,'Nueva activdad padrima final','Terminada las actividades','Reflexión','3');
/*!40000 ALTER TABLE `actividad` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `administrador`
--

DROP TABLE IF EXISTS `administrador`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `administrador` (
  `idAdministrativo` int NOT NULL AUTO_INCREMENT,
  `contraseña` varchar(245) DEFAULT NULL,
  `nombreAdministrador` varchar(45) NOT NULL,
  `rol` varchar(45) NOT NULL,
  `usuario` varchar(45) NOT NULL,
  PRIMARY KEY (`idAdministrativo`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrador`
--

LOCK TABLES `administrador` WRITE;
/*!40000 ALTER TABLE `administrador` DISABLE KEYS */;
INSERT INTO `administrador` VALUES (4,'182832f1adc3edf6d9ad6dfcef9da7aa0324c4acf64730e1725693ad0ae29f78','Rodrigo Perez','Administrador','rPerez');
/*!40000 ALTER TABLE `administrador` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colaboraciones_registradas`
--

DROP TABLE IF EXISTS `colaboraciones_registradas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colaboraciones_registradas` (
  `Profesor_idProfesor` int NOT NULL,
  `Colaboración_idColaboración` int NOT NULL,
  KEY `fk_Profesor_has_Colaboración_Profesor1_idx` (`Profesor_idProfesor`),
  KEY `fk_Profesor_has_Colaboración_Colaboración1_idx` (`Colaboración_idColaboración`),
  CONSTRAINT `fk_Profesor_has_Colaboración_Colaboración1` FOREIGN KEY (`Colaboración_idColaboración`) REFERENCES `colaboración` (`idColaboración`),
  CONSTRAINT `fk_Profesor_has_Colaboración_Profesor1` FOREIGN KEY (`Profesor_idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colaboraciones_registradas`
--

LOCK TABLES `colaboraciones_registradas` WRITE;
/*!40000 ALTER TABLE `colaboraciones_registradas` DISABLE KEYS */;
INSERT INTO `colaboraciones_registradas` VALUES (36,45),(36,46),(36,47),(40,47),(39,46);
/*!40000 ALTER TABLE `colaboraciones_registradas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `colaboración`
--

DROP TABLE IF EXISTS `colaboración`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `colaboración` (
  `idColaboración` int NOT NULL AUTO_INCREMENT,
  `nombreColaboración` varchar(245) NOT NULL,
  `descripción` text NOT NULL,
  `tipoColaboración` varchar(15) NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `estado` varchar(245) DEFAULT NULL,
  `objetivo` text NOT NULL,
  `temaInterés` varchar(245) NOT NULL,
  `noEstudiantes` int DEFAULT NULL,
  `perfilEstudiante` text,
  PRIMARY KEY (`idColaboración`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colaboración`
--

LOCK TABLES `colaboración` WRITE;
/*!40000 ALTER TABLE `colaboración` DISABLE KEYS */;
INSERT INTO `colaboración` VALUES (45,'Nueva Colaboración','Descripción de una nueva colaboración','','2024-06-19','2024-06-21','Aceptada','Crear colaboración','Comunicación',24,'Aprender'),(46,'Nueva propuesta','Nueva descripción','','2024-05-29','2024-06-22','Cerrada','Probar','Colaborar',12,'asasass'),(47,'Bonjour al frances','Aprendizaje del idioma frances','Clase espejo','2024-06-01','2024-07-06','Cerrada','Entendimiento del idioma frances','Idioma',11,'Internacional');
/*!40000 ALTER TABLE `colaboración` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `cronograma_actividades`
--

DROP TABLE IF EXISTS `cronograma_actividades`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cronograma_actividades` (
  `idColaboración` int DEFAULT NULL,
  `idActividad` int DEFAULT NULL,
  KEY `idColaboración` (`idColaboración`),
  KEY `idActividad` (`idActividad`),
  CONSTRAINT `cronograma_actividades_ibfk_1` FOREIGN KEY (`idColaboración`) REFERENCES `colaboración` (`idColaboración`),
  CONSTRAINT `cronograma_actividades_ibfk_2` FOREIGN KEY (`idActividad`) REFERENCES `actividad` (`idActividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cronograma_actividades`
--

LOCK TABLES `cronograma_actividades` WRITE;
/*!40000 ALTER TABLE `cronograma_actividades` DISABLE KEYS */;
INSERT INTO `cronograma_actividades` VALUES (46,12),(46,14),(46,16);
/*!40000 ALTER TABLE `cronograma_actividades` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estudiante`
--

DROP TABLE IF EXISTS `estudiante`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estudiante` (
  `correoElectrónico` varchar(255) NOT NULL,
  `Profesor_idProfesor` int NOT NULL,
  `contraseña` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`correoElectrónico`),
  KEY `fk_Estudiante_Profesor1_idx` (`Profesor_idProfesor`),
  CONSTRAINT `fk_Estudiante_Profesor1` FOREIGN KEY (`Profesor_idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estudiante`
--

LOCK TABLES `estudiante` WRITE;
/*!40000 ALTER TABLE `estudiante` DISABLE KEYS */;
INSERT INTO `estudiante` VALUES ('aguilar.ma@gmail.com',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8'),('Correo@ejemplo.com',29,'contraseñaEstudiante'),('estudiantenuevo@uv.mx',36,'d04e1218c19b6fcf0e133a261b34cb772ba03bba2088bb805487388d0462bbe0'),('haduromi@estudiantes.uv.mx',36,'f5e47ddb11c8c994deba11a990fd3e07b6bfa34865a94d5f234c0c1833a634e3'),('jolomanto@estudiantes.uv.mx',36,'e2d7358e570704527071978d87fb35e84fa4c9cc01cbfe96f45ec72c31d3f6d5'),('loromaro@estudiantes.uv.mx',36,'359e33bed963d77abed9dd612e94bcfb6df34fbacbbf5e62ece3af6ae3d332d5'),('marla@gmail.com',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8'),('p',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8'),('test@test',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8'),('torreo@ejemplo.com',29,'cdb4ee2aea69cc6a83331bbe96dc2caa9a299d21329efb0336fc02a82e1839a8'),('zS22013641@estudiantes.uv.mx',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8'),('zs22014567@estudiantes.uv.mx',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8'),('zs22331616@estudiantes.uv.mx',36,'b58302ec32ceaf0dd392d648f4fd18422b5b938ce59cd783d62e460790308ca0'),('zs346787@estudiantes.uv.mx',30,'97a62ad21d79c01cceb7767952acec4fec86bfe909b06e5f3f6963365cf91ab8');
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `información_requerida`
--

DROP TABLE IF EXISTS `información_requerida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `información_requerida` (
  `area_academica` varchar(255) DEFAULT NULL,
  `region` varchar(255) DEFAULT NULL,
  `tipoContratación` varchar(255) DEFAULT NULL,
  `categoríaContratación` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `información_requerida`
--

LOCK TABLES `información_requerida` WRITE;
/*!40000 ALTER TABLE `información_requerida` DISABLE KEYS */;
INSERT INTO `información_requerida` VALUES ('Técnica',NULL,NULL,NULL),('Humanidades',NULL,NULL,NULL),('Económico-Administrativo',NULL,NULL,NULL),('Ciencias de la salud',NULL,NULL,NULL),('Biológico-Agropecuarias',NULL,NULL,NULL),('AFGB',NULL,NULL,NULL),('DGRI',NULL,NULL,NULL),(NULL,'Xalapa',NULL,NULL),(NULL,'Veracruz',NULL,NULL),(NULL,'Orizaba-Cordoba',NULL,NULL),(NULL,'Poza Rica-Tuxpan',NULL,NULL),(NULL,'Coatzacoalcos',NULL,NULL),(NULL,NULL,'Planta',NULL),(NULL,NULL,'Interino por plaza',NULL),(NULL,NULL,'Interino por persona',NULL),(NULL,NULL,'Interino por tiempo determinado',NULL),(NULL,NULL,'Interino por obra determinada',NULL),(NULL,NULL,'Interino por falta de grado',NULL),(NULL,NULL,'Suplente o sustituto',NULL),(NULL,NULL,'Trabajos específicos',NULL),(NULL,NULL,'Interino por plaza con plaza',NULL),(NULL,NULL,'Interino por persona con plaza',NULL),(NULL,NULL,'Supletente o sustituto con plaza',NULL),(NULL,NULL,'Eventual',NULL),(NULL,NULL,'Beca trabajo',NULL),(NULL,NULL,'Apoyo',NULL),(NULL,NULL,'Beca subsidio',NULL),(NULL,NULL,'Beca posgrado',NULL),(NULL,NULL,'Beca sistema nacional de investigación',NULL),(NULL,NULL,'Beca profesional',NULL),(NULL,NULL,NULL,'Rector'),(NULL,NULL,NULL,'Secretario'),(NULL,NULL,NULL,'Vice rector'),(NULL,NULL,NULL,'Director general académico'),(NULL,NULL,NULL,'Director de unidad académica'),(NULL,NULL,NULL,'Director académico'),(NULL,NULL,NULL,'Secretario de unidad académica'),(NULL,NULL,NULL,'Secretario académico'),(NULL,NULL,NULL,'Coordinador de asesores'),(NULL,NULL,NULL,'Coordinador general'),(NULL,NULL,NULL,'Director general administrativo'),(NULL,NULL,NULL,'Director administrativo'),(NULL,NULL,NULL,'Director de hospital'),(NULL,NULL,NULL,'Jefe de carrera'),(NULL,NULL,NULL,'Director artístico'),(NULL,NULL,NULL,'Coordinador académico'),(NULL,NULL,NULL,'Jefe de departamento'),(NULL,NULL,NULL,'Jefe de oficina'),(NULL,NULL,NULL,'Administrador'),(NULL,NULL,NULL,'Coordinador de laboratorio'),(NULL,NULL,NULL,'Oficial administrativo C'),(NULL,NULL,NULL,'Oficial administrativo B'),(NULL,NULL,NULL,'Oficial administrativo A'),(NULL,NULL,NULL,'Auxiliar administrativo'),(NULL,NULL,NULL,'Ayudante de oficina'),(NULL,NULL,NULL,'Oficial técnico B'),(NULL,NULL,NULL,'Oficial técnico A'),(NULL,NULL,NULL,'Auxiliar técnico'),(NULL,NULL,NULL,'Ayudante técnico'),(NULL,NULL,NULL,'Ayudante de mantenimiento'),(NULL,NULL,NULL,'Ayudante de taller y campo'),(NULL,NULL,NULL,'Oficial de independencia'),(NULL,NULL,NULL,'Auxiliar de control'),(NULL,NULL,NULL,'Auxiliar de oficina'),(NULL,NULL,NULL,'Auxiliar de vigilancia'),(NULL,NULL,NULL,'Auxiliar de independencia'),(NULL,NULL,NULL,'Auxiliar de campo'),(NULL,NULL,NULL,'Analista D'),(NULL,NULL,NULL,'Analista C'),(NULL,NULL,NULL,'Analista B'),(NULL,NULL,NULL,'Analista A'),(NULL,NULL,NULL,'Oficial D'),(NULL,NULL,NULL,'Oficial C'),(NULL,NULL,NULL,'Oficial B'),(NULL,NULL,NULL,'Oficial A'),(NULL,NULL,NULL,'Secretaria ejecutiva D'),(NULL,NULL,NULL,'Secretaria ejecutiva C'),(NULL,NULL,NULL,'Secretaria ejecutiva B'),(NULL,NULL,NULL,'Secretaria ejecutiva A'),(NULL,NULL,NULL,'T.C. Acad. Carrera titular C'),(NULL,NULL,NULL,'T.C. Acad. Carrera titular B'),(NULL,NULL,NULL,'T.C. Acad. Carrera titular A'),(NULL,NULL,NULL,'T.C. Acad. Carrera asociado C'),(NULL,NULL,NULL,'T.C. Acad. Carrera asociado B'),(NULL,NULL,NULL,'T.C. Acad. Carrera asociado A'),(NULL,NULL,NULL,'T.C Tec. Acad. Titular C'),(NULL,NULL,NULL,'T.C Tec. Acad. Titular B'),(NULL,NULL,NULL,'T.C Tec. Acad. Titular A'),(NULL,NULL,NULL,'T.C Tec. Acad. Asociado C'),(NULL,NULL,NULL,'T.C Tec. Acad. Asociado B'),(NULL,NULL,NULL,'T.C Tec. Acad. Asociado A'),(NULL,NULL,NULL,'M.T. Acad. Carrera titular C'),(NULL,NULL,NULL,'M.T. Acad. Carrera titular B'),(NULL,NULL,NULL,'M.T. Acad. Carrera titular A'),(NULL,NULL,NULL,'M.T. Acad. Carrera asociado C'),(NULL,NULL,NULL,'M.T. Acad. Carrera asociado B'),(NULL,NULL,NULL,'M.T. Acad. Carrera asociado A'),(NULL,NULL,NULL,'M.T. Tec. Acad. Titular C'),(NULL,NULL,NULL,'M.T. Tec. Acad. Titular B'),(NULL,NULL,NULL,'M.T. Tec. Acad. Titular A'),(NULL,NULL,NULL,'M.T. Tec. Acad. Asociado C'),(NULL,NULL,NULL,'M.T. Tec. Acad. Asociado B'),(NULL,NULL,NULL,'M.T. Tec. Acad. Asociado A'),(NULL,NULL,NULL,'Prof. Asignatura B'),(NULL,NULL,NULL,'Prof. Asignatura A'),(NULL,NULL,NULL,'Acad. Instructor en especialidades médicas'),(NULL,NULL,NULL,'Acad. Instructor químico clínico'),(NULL,NULL,NULL,'Acad. Instructor nutriólogo'),(NULL,NULL,NULL,'Acad. Instructor técnico-histotecnólogo'),(NULL,NULL,NULL,'Acad. Instructor técnico-radiólogo'),(NULL,NULL,NULL,'Acad. Instructor en medicina general'),(NULL,NULL,NULL,'Acad. Instructor en enfermería especialista'),(NULL,NULL,NULL,'Acad. Instructor en enfermería general'),(NULL,NULL,NULL,'Eventual'),(NULL,NULL,NULL,'Beca trabajo A'),(NULL,NULL,NULL,'Beca trabajo B'),(NULL,NULL,NULL,'Beca trabajo C'),(NULL,NULL,NULL,'Beca trabajo D'),(NULL,NULL,NULL,'Beca trabajo E'),(NULL,NULL,NULL,'Beca trabajo F'),(NULL,NULL,NULL,'Beca trabajo G'),(NULL,NULL,NULL,'Beca trabajo H'),(NULL,NULL,NULL,'Beca trabajo I'),(NULL,NULL,NULL,'Beca trabajo J'),(NULL,NULL,NULL,'Beca trabajo K'),(NULL,NULL,NULL,'Beca trabajo L'),(NULL,NULL,NULL,'Beca trabajo M'),(NULL,NULL,NULL,'Subsidio hijos Trabaj. Académicos Z A'),(NULL,NULL,NULL,'Subsidio hijos Trabaj. Académicos Z B'),(NULL,NULL,NULL,'Subsidio hijos Trabaj. Académicos Z C'),(NULL,NULL,NULL,'Subsidio hijos Trabaj. Confianza'),(NULL,NULL,NULL,'Subsidio hijos Trabaj. Admvo. Tec. Y Man.'),(NULL,NULL,NULL,'Becas posgrado'),(NULL,NULL,NULL,'Beca del sistema nacional de Invest.'),(NULL,NULL,NULL,'Beca profesional A'),(NULL,NULL,NULL,'Beca profesional B'),(NULL,NULL,NULL,'Beca profesional C'),(NULL,NULL,NULL,'Beca profesional D'),(NULL,NULL,NULL,'Beca profesional E'),(NULL,NULL,NULL,'Beca profesional F'),(NULL,NULL,NULL,'Embargo judicial'),(NULL,NULL,NULL,'Apoyo');
/*!40000 ALTER TABLE `información_requerida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `participa`
--

DROP TABLE IF EXISTS `participa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `participa` (
  `Estudiante_correoElectrónico` varchar(255) NOT NULL,
  `Colaboración_idColaboración` int NOT NULL,
  KEY `fk_Estudiante_has_Colaboración_Colaboración1_idx` (`Colaboración_idColaboración`),
  KEY `fk_Estudiante_has_Colaboración_Estudiante1_idx` (`Estudiante_correoElectrónico`),
  CONSTRAINT `fk_Estudiante_has_Colaboración_Colaboración1` FOREIGN KEY (`Colaboración_idColaboración`) REFERENCES `colaboración` (`idColaboración`),
  CONSTRAINT `fk_Estudiante_has_Colaboración_Estudiante1` FOREIGN KEY (`Estudiante_correoElectrónico`) REFERENCES `estudiante` (`correoElectrónico`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `participa`
--

LOCK TABLES `participa` WRITE;
/*!40000 ALTER TABLE `participa` DISABLE KEYS */;
INSERT INTO `participa` VALUES ('loromaro@estudiantes.uv.mx',47);
/*!40000 ALTER TABLE `participa` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `profesor`
--

DROP TABLE IF EXISTS `profesor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `profesor` (
  `idProfesor` int NOT NULL AUTO_INCREMENT,
  `nombreProfesor` varchar(255) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `tipoProfesor` varchar(45) NOT NULL,
  `país` varchar(45) NOT NULL,
  `idioma` varchar(45) NOT NULL,
  `Universidad_idUniversidad` int NOT NULL,
  `area_academica` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `contraseña` varchar(100) DEFAULT NULL,
  `NoPersonal` int DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `tipoContratación` varchar(45) DEFAULT NULL,
  `categoríaContratación` varchar(45) DEFAULT NULL,
  `disciplina` varchar(45) DEFAULT NULL,
  `curso_taller` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `uk_usuario` (`usuario`),
  UNIQUE KEY `uk_correo` (`correo`),
  UNIQUE KEY `No.Personal_UNIQUE` (`NoPersonal`),
  KEY `fk_Profesor_Universidad_idx` (`Universidad_idUniversidad`),
  CONSTRAINT `fk_Profesor_Universidad` FOREIGN KEY (`Universidad_idUniversidad`) REFERENCES `universidad` (`idUniversidad`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8mb3 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (35,'Daniel Alejandre','daniel_daniel1713','2727846902','Rechazado','UV','México','Español',6,'Técnica','daur00704@outlook.com','1136f4e9c80b54f542434ea5b7a94b036004f656d42b0c43f2869aeb88769d9d',2561,'Orizaba-Cordoba','Planta','Director académico',NULL,'Sí'),(36,'Norma Alejandre','norma_norma2766','2721138529','Aceptado','Externo','México','Español',7,NULL,'rosas97@yahoo.com','c6eb09dab265c3b08cbae2623b27b7e48a354be3c3ac0d297de7f54a75d097b6',NULL,NULL,NULL,NULL,NULL,'Sí'),(37,'Jorge Alberto','Usuario de prueba uv1','2220000000','Aceptado','UV','México','Inglés',6,'Económico','test2@email.com','37268335dd6931045bdcdf92623ff819a64244b53d0e746d438797349d4da578',222222,'Xalapa','luegocheco','luegochecox2',NULL,NULL),(38,'Andres Flores','andres_andres1995','2418549630','Pendiente','Externo','Belgica','Ingles',8,NULL,'danielgppc@outlook.com','79f1e606e8e89beafae6c13826a0c3b3ee6e3b4b1a52f60fcbf73489c2dd637b',NULL,NULL,NULL,NULL,NULL,'Sí'),(39,'Jose Louder','jose_jose4489','4445553692','Aceptado','Externo','Estados Unidos','Ingles',9,NULL,'jlouder020212@yahoo.com','9f599575796c0fd6d7b3834c784d963f037d151e1f3778dc1b4484827a01ce87',NULL,NULL,NULL,NULL,NULL,'No'),(40,'Julian Gutierrez','julian_julian1960','2541023601','Aceptado','UV','México','Español',6,'Ciencias de la salud','knighthollow7821@uv.mx','82289ae5f85d727a080c9c4d2880969c91290ecc8fa41be84909e816d57a7e49',21451,'Poza Rica-Tuxpan','Suplente o sustituto','Auxiliar de oficina',NULL,'Sí');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retroalimentación_académicos`
--

DROP TABLE IF EXISTS `retroalimentación_académicos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retroalimentación_académicos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calificación` float DEFAULT NULL,
  `observaciones` text,
  `idColaboración` int DEFAULT NULL,
  `idProfesor` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idColaboración` (`idColaboración`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `retroalimentación_académicos_ibfk_2` FOREIGN KEY (`idColaboración`) REFERENCES `colaboración` (`idColaboración`),
  CONSTRAINT `retroalimentación_académicos_ibfk_3` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retroalimentación_académicos`
--

LOCK TABLES `retroalimentación_académicos` WRITE;
/*!40000 ALTER TABLE `retroalimentación_académicos` DISABLE KEYS */;
INSERT INTO `retroalimentación_académicos` VALUES (1,4,'Mala colaboracion\n',47,36);
/*!40000 ALTER TABLE `retroalimentación_académicos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retroalimentación_administrativos`
--

DROP TABLE IF EXISTS `retroalimentación_administrativos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retroalimentación_administrativos` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calificación` float DEFAULT NULL,
  `observaciones` text,
  `idColaboración` int DEFAULT NULL,
  `idAdministrativo` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idColaboración` (`idColaboración`),
  KEY `idAdministrativo` (`idAdministrativo`),
  CONSTRAINT `retroalimentación_administrativos_ibfk_1` FOREIGN KEY (`idColaboración`) REFERENCES `colaboración` (`idColaboración`),
  CONSTRAINT `retroalimentación_administrativos_ibfk_2` FOREIGN KEY (`idAdministrativo`) REFERENCES `administrador` (`idAdministrativo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retroalimentación_administrativos`
--

LOCK TABLES `retroalimentación_administrativos` WRITE;
/*!40000 ALTER TABLE `retroalimentación_administrativos` DISABLE KEYS */;
/*!40000 ALTER TABLE `retroalimentación_administrativos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retroalimentación_estudiantes`
--

DROP TABLE IF EXISTS `retroalimentación_estudiantes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retroalimentación_estudiantes` (
  `id` int NOT NULL AUTO_INCREMENT,
  `calificación` float DEFAULT NULL,
  `observaciones` text,
  `idColaboración` int DEFAULT NULL,
  `correoElectrónico` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `correoElectrónico` (`correoElectrónico`),
  KEY `idColaboración` (`idColaboración`),
  CONSTRAINT `retroalimentación_estudiantes_ibfk_1` FOREIGN KEY (`correoElectrónico`) REFERENCES `estudiante` (`correoElectrónico`),
  CONSTRAINT `retroalimentación_estudiantes_ibfk_2` FOREIGN KEY (`idColaboración`) REFERENCES `colaboración` (`idColaboración`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retroalimentación_estudiantes`
--

LOCK TABLES `retroalimentación_estudiantes` WRITE;
/*!40000 ALTER TABLE `retroalimentación_estudiantes` DISABLE KEYS */;
/*!40000 ALTER TABLE `retroalimentación_estudiantes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `solicitud_colaboración`
--

DROP TABLE IF EXISTS `solicitud_colaboración`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `solicitud_colaboración` (
  `idColaboración` int DEFAULT NULL,
  `idProfesor` int DEFAULT NULL,
  `estado` char(255) DEFAULT NULL,
  KEY `idColaboración` (`idColaboración`),
  KEY `idProfesor` (`idProfesor`),
  CONSTRAINT `solicitud_colaboración_ibfk_1` FOREIGN KEY (`idColaboración`) REFERENCES `colaboración` (`idColaboración`),
  CONSTRAINT `solicitud_colaboración_ibfk_2` FOREIGN KEY (`idProfesor`) REFERENCES `profesor` (`idProfesor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `solicitud_colaboración`
--

LOCK TABLES `solicitud_colaboración` WRITE;
/*!40000 ALTER TABLE `solicitud_colaboración` DISABLE KEYS */;
INSERT INTO `solicitud_colaboración` VALUES (47,40,'Aceptado'),(46,40,'Pendiente'),(46,39,'Aceptado');
/*!40000 ALTER TABLE `solicitud_colaboración` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `universidad`
--

DROP TABLE IF EXISTS `universidad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `universidad` (
  `idUniversidad` int NOT NULL AUTO_INCREMENT,
  `país` varchar(45) NOT NULL,
  `nombreUniversidad` varchar(245) NOT NULL,
  `idioma` varchar(45) NOT NULL,
  PRIMARY KEY (`idUniversidad`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidad`
--

LOCK TABLES `universidad` WRITE;
/*!40000 ALTER TABLE `universidad` DISABLE KEYS */;
INSERT INTO `universidad` VALUES (6,'México','Universidad Veracruzana','Español'),(7,'México','UNAM','Español'),(8,'Belgica','University of Ghent','Ingles'),(9,'Estados Unidos','MIT','Ingles');
/*!40000 ALTER TABLE `universidad` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-06-22 18:12:44
