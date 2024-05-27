-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: coil_Vic
-- ------------------------------------------------------
-- Server version	8.0.36

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

-- -----------------------------------------------------
-- Schema coil_Vic
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `coil_vic` ;

-- -----------------------------------------------------
-- Schema coil_Vic
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `coil_vic` DEFAULT CHARACTER SET utf8 ;
USE `coil_vic` ;
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
INSERT INTO `administrador` VALUES (4,'182832f1adc3edf6d9ad6dfcef9da7aa0324c4acf64730e1725693ad0ae29f78','AdminNombreTest','RolTest','UsuarioAdminTest');
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
INSERT INTO `colaboraciones_registradas` VALUES (24,29),(24,36),(24,37);
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
  `colaboracióncol` varchar(45) DEFAULT NULL,
  `nombreColaboración` varchar(45) NOT NULL,
  `descripción` text NOT NULL,
  `fechaInicio` date NOT NULL,
  `fechaFin` date NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `objetivo` text NOT NULL,
  `temaInterés` varchar(45) NOT NULL,
  `noEstudiantes` int DEFAULT NULL,
  `perfilEstudiante` text,
  PRIMARY KEY (`idColaboración`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `colaboración`
--

LOCK TABLES `colaboración` WRITE;
/*!40000 ALTER TABLE `colaboración` DISABLE KEYS */;
INSERT INTO `colaboración` VALUES (29,NULL,'Comunicación en lengua entranjera','TestDescripcion','2022-01-01','2026-02-01','Activa','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(30,NULL,'Comunicación en lengua entranjera','TestDescripcion','2023-01-01','2026-02-01','Activa','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(31,NULL,'Cyberseguridad','Enseñar a los estudiantes acerca de la cyberseguridad y su importancia.','2022-01-01','2026-02-01','En revisión','Unir estudiantes','Trabajo colaborativo',25,'Inglés B2,'),(32,NULL,'Interculturalidad','si','2022-01-01','2026-02-01','En revisión','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(33,NULL,'Idiomas','TestDescripcion','2022-01-01','2026-02-01','Rechazada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(34,NULL,'Historia del mundo','TestDescripcion','2022-01-01','2026-02-01','En revisión','','Trabajo colaborativo',25,'Inglés, '),(35,NULL,'Culturas','TestDescripcion','2022-01-01','2026-02-01','Aceptad','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(36,NULL,'Culturas','TestDescripcion','2022-01-01','2026-02-01','Publicada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(37,NULL,'Comunicación sin fornteras','TestDescripcion','2022-01-01','2026-02-01','Publicada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(38,NULL,'Comunicación sin fornteras','TestDescripcion','2022-01-01','2026-02-01','Aceptada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(39,NULL,'Comunicación sin fornteras','TestDescripcion','2022-01-01','2026-02-01','Aceptada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(40,NULL,'Comunicación sin fronteras','TestDescripcion','2022-01-01','2026-02-01','Aceptada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, '),(41,NULL,'Innovación tecnológica responsable','TestDescripcion','2022-01-01','2026-02-01','Rechazada','Unir estudiantes','Trabajo colaborativo',25,'Inglés, ');
/*!40000 ALTER TABLE `colaboración` ENABLE KEYS */;
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
  `contraseña` varchar(45) NOT NULL,
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
/*!40000 ALTER TABLE `estudiante` ENABLE KEYS */;
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
  `nombreProfesor` varchar(45) NOT NULL,
  `usuario` varchar(50) NOT NULL,
  `telefono` varchar(13) NOT NULL,
  `estado` varchar(45) NOT NULL,
  `tipoProfesor` varchar(45) NOT NULL,
  `país` varchar(45) NOT NULL,
  `Universidad_idUniversidad` int NOT NULL,
  `area_academica` varchar(255) DEFAULT NULL,
  `correo` varchar(255) DEFAULT NULL,
  `contraseña` varchar(100) DEFAULT NULL,
  `No.Personal` int DEFAULT NULL,
  `region` varchar(45) DEFAULT NULL,
  `tipoContratación` varchar(45) DEFAULT NULL,
  `categoríaContratación` varchar(45) DEFAULT NULL,
  `disciplina` varchar(45) DEFAULT NULL,
  `curso_taller` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`idProfesor`),
  UNIQUE KEY `uk_usuario` (`usuario`),
  UNIQUE KEY `uk_correo` (`correo`),
  UNIQUE KEY `No.Personal_UNIQUE` (`No.Personal`),
  KEY `fk_Profesor_Universidad_idx` (`Universidad_idUniversidad`),
  CONSTRAINT `fk_Profesor_Universidad` FOREIGN KEY (`Universidad_idUniversidad`) REFERENCES `universidad` (`idUniversidad`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb3 COMMENT='	';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `profesor`
--

LOCK TABLES `profesor` WRITE;
/*!40000 ALTER TABLE `profesor` DISABLE KEYS */;
INSERT INTO `profesor` VALUES (24,'Juan Díaz','Usuario de prueba','2720000000','Aceptado','UV','México',1,'Humanidades','test@email.com','testtest',NULL,'Xalapa',NULL,NULL,NULL,NULL),(25,'Jorge Alberto','Usuario de prueba2','2220000000','Aceptado','UV','México',1,'Económico','test2@email.com','37268335dd6931045bdcdf92623ff819a64244b53d0e746d438797349d4da578',NULL,NULL,NULL,NULL,NULL,NULL),(28,'Jorge Alberto','jalberto','2220000000','Aceptado','UV','México',1,'Económico','jorgel@gamil.com','37268335dd6931045bdcdf92623ff819a64244b53d0e746d438797349d4da578',NULL,NULL,NULL,NULL,NULL,'Sí'),(29,'Juan Jimenez','jHuan','2220000000','Aceptado','UV','México',1,'Económico','jjimenez@gmail.com','37268335dd6931045bdcdf92623ff819a64244b53d0e746d438797349d4da578',NULL,NULL,NULL,NULL,NULL,'Sí'),(30,'Pedro Jimenez','jPedro','2220000000','Aceptado','UV','México',1,'Económico','pppimenz@gmail.com','37268335dd6931045bdcdf92623ff819a64244b53d0e746d438797349d4da578',NULL,NULL,NULL,NULL,NULL,'Sí');
/*!40000 ALTER TABLE `profesor` ENABLE KEYS */;
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
INSERT INTO `solicitud_colaboración` VALUES (33,24, "Pendiente"),(36,24, "Pendiente"),(36,25, "Pendiente"),(36,25, "Pendiente"),(37,25, "Pendiente"),(36,25, "Pendiente");
/*!40000 ALTER TABLE `solicitud_colaboración` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `syllabus`
--

DROP TABLE IF EXISTS `actividad`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `actividad` (
  `idActividad` int NOT NULL AUTO_INCREMENT,
  `título` varchar(30) NOT NULL,
  `descripcion` varchar(200) NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `semana` varchar(45) NOT NULL, 
  PRIMARY KEY (`idActividad`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Actividad`
--

LOCK TABLES `Actividad` WRITE;
/*!40000 ALTER TABLE `Actividad` DISABLE KEYS */;
/*!40000 ALTER TABLE `Actividad` ENABLE KEYS */;
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
  `nombreUniversidad` varchar(45) NOT NULL,
  `idioma` varchar(45) NOT NULL,
  PRIMARY KEY (`idUniversidad`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `universidad`
--

LOCK TABLES `universidad` WRITE;
/*!40000 ALTER TABLE `universidad` DISABLE KEYS */;
INSERT INTO `universidad` VALUES (1,'México','Universidad Veracruzana','Español');
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

-- Dump completed on 2024-05-19 18:29:03
DROP TABLE IF EXISTS `retroalimentación_estudiantes`;
CREATE TABLE retroalimentación_estudiantes (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calificación FLOAT,
    observaciones TEXT,
    idColaboración INT,
    idAdministrativo INT,
    correoElectrónico VARCHAR(255),
    FOREIGN KEY (correoElectrónico) REFERENCES estudiante (correoElectrónico),
    FOREIGN KEY (idColaboración) REFERENCES colaboración (idColaboración),
    FOREIGN KEY (idAdministrativo) REFERENCES administrador (idAdministrativo)
);

DROP TABLE IF EXISTS `retroalimentación_académicos`;
CREATE TABLE retroalimentación_académicos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calificación FLOAT,
    observaciones TEXT,
    idColaboración INT,
    idAdministrativo INT,
    correoElectrónico VARCHAR(255),
    FOREIGN KEY (correoElectrónico) REFERENCES profesor (correo),
    FOREIGN KEY (idColaboración) REFERENCES colaboración (idColaboración),
    FOREIGN KEY (idAdministrativo) REFERENCES administrador (idAdministrativo)
);

DROP TABLE IF EXISTS `retroalimentación_administrativos`;
CREATE TABLE retroalimentación_administrativos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    calificación FLOAT,
    observaciones TEXT,
    idColaboración INT,
    idAdministrativo INT,
    correoElectrónico VARCHAR(255),
    FOREIGN KEY (idColaboración) REFERENCES colaboración (idColaboración),
    FOREIGN KEY (idAdministrativo) REFERENCES administrador (idAdministrativo)
);

DROP TABLE IF EXISTS `cronograma_actividades`;
CREATE TABLE cronograma_actividades (
    idColaboración INT,
    idActividad INT,
    FOREIGN KEY (idColaboración) REFERENCES colaboración (idColaboración),
    FOREIGN KEY (idActividad) REFERENCES actividad (idActividad)
);





 