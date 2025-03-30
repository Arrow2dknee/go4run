CREATE TABLE IF NOT EXISTS activity
(
    id INTEGER NOT NULL,
    title varchar(250) NOT NULL,
    started_on timestamp NOT NULL,
    completed_on timestamp NOT NULL,
    duration_in_minutes INTEGER NOT NULL,
    activity_type varchar(250) NOT NULL,
    activity_location varchar(250) NOT NULL,
    PRIMARY KEY(id)
);