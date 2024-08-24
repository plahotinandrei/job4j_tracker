--liquibase formatted sql
--changeset plahotinandrei:2
ALTER TABLE items ADD COLUMN checked BOOLEAN NOT NULL DEFAULT false;