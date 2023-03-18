-- Create role and user
-- CREATE ROLE alizuser WITH LOGIN PASSWORD 'aliz';
-- ALTER ROLE alizuser CREATEDB;

-- Create schema (if needed)
-- CREATE SCHEMA IF NOT EXISTS skillmatrix;

-- Create SkillType table
CREATE TABLE skill_type (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE
);

-- Create Skill table
CREATE TABLE skill (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL UNIQUE,
    skill_type_id INTEGER NOT NULL,
    FOREIGN KEY (skill_type_id) REFERENCES skill_type(id)
);

-- Create ProficiencyLevel table
CREATE TABLE proficiency_level (
    id SERIAL PRIMARY KEY,
    level_name VARCHAR(255) NOT NULL UNIQUE,
    level_value INTEGER NOT NULL UNIQUE
);

-- Create User table
CREATE TABLE aliz_user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE
);

-- Create UserSkill table
CREATE TABLE user_skill (
    id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL,
    skill_id INTEGER NOT NULL,
    proficiency_level_id INTEGER NOT NULL,
    FOREIGN KEY (user_id) REFERENCES aliz_user(id),
    FOREIGN KEY (skill_id) REFERENCES skill(id),
    FOREIGN KEY (proficiency_level_id) REFERENCES proficiency_level(id),
    UNIQUE(user_id, skill_id)
);

