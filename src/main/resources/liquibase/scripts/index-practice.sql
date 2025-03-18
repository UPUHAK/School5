-- liquibase formatted sql

-- changeset UPUHAK:1
CREATE INDEX idx_student_name ON Student (name);

-- changeset UPUHAK:2
CREATE INDEX idx_faculty_name_color ON Faculty (name, color);