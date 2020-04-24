USE referencee_db;

CREATE TABLE user (
	email VARCHAR(256) NOT NULL PRIMARY KEY,
    admin BOOLEAN DEFAULT FALSE
);

CREATE TABLE reference (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(256) NOT NULL,
    approved BOOLEAN DEFAULT FALSE,
    url VARCHAR(1024) NOT NULL UNIQUE,
    gcs_path VARCHAR(1024) NOT NULL UNIQUE,
    gender ENUM('male', 'female') NOT NULL,
    clothing ENUM('nude', 'clothed') NOT NULL,
    pose ENUM('action', 'static') NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user(email) ON UPDATE CASCADE ON DELETE CASCADE
);

CREATE TABLE event (
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_email VARCHAR(256) NOT NULL,
    date DATE NOT NULL,
    time INT NOT NULL,
    FOREIGN KEY (user_email) REFERENCES user(email) ON UPDATE CASCADE ON DELETE CASCADE
);

/**************************************************************************************
 *                                        user                                        *
 **************************************************************************************/

CREATE PROCEDURE create_user (user_email VARCHAR(256))
BEGIN
	INSERT INTO user (email)
    VALUES (user_email);
END;

CREATE PROCEDURE create_admin (user_email VARCHAR(256))
BEGIN
	INSERT INTO user (email, admin)
    VALUES (user_email, TRUE);
END;

CREATE PROCEDURE read_user_admin_by_email (user_email VARCHAR(256))
BEGIN
	SELECT user.admin user_admin
    FROM user
    WHERE user.email = user_email;
END;

/**************************************************************************************
 *                                     reference                                      *
 **************************************************************************************/

CREATE PROCEDURE create_reference (
    ref_user_email VARCHAR(256),
    ref_url VARCHAR(1024),
    ref_gcs_path VARCHAR(1024),
    ref_gender VARCHAR(10),
    ref_clothing VARCHAR(10),
    ref_pose VARCHAR(10))
BEGIN
	INSERT INTO reference (user_email, url, gcs_path, gender, clothing, pose)
    VALUES (ref_user_email, ref_url, ref_gcs_path, ref_gender, ref_clothing, ref_pose);
END;

CREATE PROCEDURE read_reference_approved ()
BEGIN
	SELECT reference.id ref_id,
           reference.user_email ref_user_email,
           reference.approved ref_approved,
           reference.url ref_url,
           reference.gcs_path ref_gcs_path,
           reference.gender ref_gender,
           reference.clothing ref_clothing,
           reference.pose ref_pose
	FROM reference
    WHERE reference.approved = TRUE;
END;

CREATE PROCEDURE read_reference_not_approved ()
BEGIN
	SELECT reference.id ref_id,
           reference.user_email ref_user_email,
           reference.approved ref_approved,
           reference.url ref_url,
           reference.gcs_path ref_gcs_path,
           reference.gender ref_gender,
           reference.clothing ref_clothing,
           reference.pose ref_pose
	FROM reference
    WHERE reference.approved = FALSE;
END;

CREATE PROCEDURE read_reference_approved_rand (ref_count INT)
BEGIN
	SELECT reference.id ref_id,
           reference.user_email ref_user_email,
           reference.approved ref_approved,
           reference.url ref_url,
           reference.gcs_path ref_gcs_path,
           reference.gender ref_gender,
           reference.clothing ref_clothing,
           reference.pose ref_pose
	FROM reference
    WHERE reference.approved = TRUE
    ORDER BY RAND()
    LIMIT ref_count;
END;

CREATE PROCEDURE read_reference_by_user_email (ref_user_email VARCHAR(256))
BEGIN
	SELECT reference.id ref_id,
           reference.user_email ref_user_email,
           reference.approved ref_approved,
           reference.url ref_url,
           reference.gcs_path ref_gcs_path,
           reference.gender ref_gender,
           reference.clothing ref_clothing,
           reference.pose ref_pose
	FROM reference
    WHERE reference.user_email = ref_user_email;
END;

CREATE PROCEDURE update_reference_approved (ref_id INT)
BEGIN
	UPDATE reference
    SET reference.approved = TRUE
    WHERE reference.id = ref_id;
END;

CREATE PROCEDURE delete_reference (ref_id INT)
BEGIN
	DELETE FROM reference
    WHERE reference.id = ref_id;
END;

/**************************************************************************************
 *                                       event                                        *
 **************************************************************************************/

CREATE PROCEDURE create_event (event_user_email VARCHAR(256), event_time INT)
BEGIN
	INSERT INTO event (user_email, date, time)
    VALUES (event_user_email, CURRENT_DATE(), event_time);
END;

CREATE PROCEDURE read_event_by_user (event_user_email VARCHAR(256))
BEGIN
	SELECT event.id event_id,
		   event.user_email event_user_email,
           event.date event_date,
           event.time event_time
	FROM event
    WHERE event.user_email = event_user_email;
END;

CREATE PROCEDURE read_event_by_user_today (event_user_email VARCHAR(256))
BEGIN
	SELECT event.id event_id,
		   event.user_email event_user_email,
           event.date event_date,
           event.time event_time
	FROM event
    WHERE event.user_email = event_user_email
		  AND event.date = CURRENT_DATE();
END;

CREATE PROCEDURE read_event_by_user_week (event_user_email VARCHAR(256))
BEGIN
	SELECT event.id event_id,
		   event.user_email event_user_email,
           event.date event_date,
           event.time event_time
	FROM event
    WHERE event.user_email = event_user_email
		  AND YEARWEEK(event.date) = YEARWEEK(CURRENT_DATE());
END;

CREATE PROCEDURE read_event_by_user_month (event_user_email VARCHAR(256))
BEGIN
	SELECT event.id event_id,
		   event.user_email event_user_email,
           event.date event_date,
           event.time event_time
	FROM event
    WHERE event.user_email = event_user_email
		  AND MONTH(event.date) = MONTH(CURRENT_DATE())
          AND YEAR(event.date) = YEAR(CURRENT_DATE());
END;

CREATE PROCEDURE read_event_by_user_year (event_user_email VARCHAR(256))
BEGIN
	SELECT event.id event_id,
		   event.user_email event_user_email,
           event.date event_date,
           event.time event_time
	FROM event
    WHERE event.user_email = event_user_email
          AND YEAR(event.date) = YEAR(CURRENT_DATE());
END;