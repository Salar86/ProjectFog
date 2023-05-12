DROP TABLE IF EXISTS `itemlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `itemlist`
(
    `itemlist_id`        int         NOT NULL AUTO_INCREMENT,
    `description`        varchar(45) NOT NULL,
    `price`              int         NOT NULL,
    `order_id`           int         NOT NULL,
    `product_variant_id` int         NOT NULL,
    PRIMARY KEY (`itemlist_id`),
    KEY                  `fk_itemlist_order1_idx` (`order_id`),
    KEY                  `fk_itemlist_product_variant1_idx` (`product_variant_id`),
    CONSTRAINT `fk_itemlist_order1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`),
    CONSTRAINT `fk_itemlist_product_variant1` FOREIGN KEY (`product_variant_id`) REFERENCES `product_variant` (`product_variant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `itemlist`
--

LOCK
TABLES `itemlist` WRITE;
/*!40000 ALTER TABLE `itemlist` DISABLE KEYS */;
/*!40000 ALTER TABLE `itemlist` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `order`
--

DROP TABLE IF EXISTS `order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order`
(
    `order_id` int         NOT NULL AUTO_INCREMENT,
    `length`   double      NOT NULL,
    `width`    double      NOT NULL,
    `material` varchar(45) NOT NULL,
    `price`    double      NOT NULL,
    `status`   varchar(45) NOT NULL,
    `user_id`  int         NOT NULL,
    PRIMARY KEY (`order_id`),
    KEY        `fk_order_user1_idx` (`user_id`),
    CONSTRAINT `fk_order_user1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order`
--

LOCK
TABLES `order` WRITE;
/*!40000 ALTER TABLE `order` DISABLE KEYS */;
/*!40000 ALTER TABLE `order` ENABLE KEYS */;
UNLOCK
TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product`
(
    `product_id`          int         NOT NULL AUTO_INCREMENT,
    `product_description` varchar(45) NOT NULL,
    `unit`                double      NOT NULL,
    `price_per_unit`      double      NOT NULL,
    PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK
TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
/*!40000 ALTER TABLE `product` ENABLE KEYS */;