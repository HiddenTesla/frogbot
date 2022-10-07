-- liquibase formatted sql

-- changeset xixi:1
CREATE TABLE IF NOT EXISTS `wish` (
    `wish_id`   BIGINT(20) NOT NULL AUTO_INCREMENT ,
    `wish_time` DATETIME   NOT NULL DEFAULT now(),
    `user_id`   BIGINT(20) NOT NULL,
    `outcome`   VARCHAR(64) NOT NULL,
    PRIMARY KEY (`wish_id`)
    ) ENGINE = innoDB DEFAULT CHARSET = utf8;
-- rollback ;
