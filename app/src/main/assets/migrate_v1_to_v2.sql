DROP TABLE mentorship_new;
CREATE TABLE mentorship_new (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    uuid TEXT UNIQUE,
    life_cycle_status TEXT,
    sync_status TEXT,
    created_at DATE,
    updated_at DATE,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    performed_date DATE NOT NULL,
    tutor_id INTEGER NOT NULL,
    tutored_id INTEGER NOT NULL,
    form_id INTEGER NOT NULL,
    session_id INTEGER NOT NULL,
    cabinet_id INTEGER NOT NULL,
    iteration_type_id INTEGER NOT NULL,
    iteration_number INTEGER NOT NULL,
    door_id INTEGER NOT NULL,
    demonstration BOOLEAN,
    demonstration_details TEXT,
    FOREIGN KEY (tutor_id) REFERENCES tutor(id),
    FOREIGN KEY (tutored_id) REFERENCES tutored(id),
    FOREIGN KEY (form_id) REFERENCES form(id),
    FOREIGN KEY (session_id) REFERENCES session(id),
    FOREIGN KEY (cabinet_id) REFERENCES cabinet(id),
    FOREIGN KEY (iteration_type_id) REFERENCES evaluation_type(id),
    FOREIGN KEY (door_id) REFERENCES door(id)
);
INSERT INTO mentorship_new (
    uuid, life_cycle_status, sync_status, created_at, updated_at,
    start_date, end_date, performed_date, tutor_id, tutored_id,
    form_id, session_id, cabinet_id, iteration_type_id, iteration_number,
    door_id, demonstration, demonstration_details
) SELECT
    uuid, life_cycle_status, sync_status, created_at, updated_at,
    start_date, end_date, performed_date, tutor_id, tutored_id,
    form_id, session_id, cabinet_id, iteration_type_id, iteration_number,
    door_id, demonstration, demonstration_details
FROM mentorship;
DROP TABLE mentorship;
ALTER TABLE mentorship_new RENAME TO mentorship;
