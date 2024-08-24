--liquibase formatted sql
--changeset plahotinandrei:3
ALTER TABLE items DROP COLUMN checked;