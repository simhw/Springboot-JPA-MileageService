DROP SCHEMA IF EXISTS triple;

CREATE
database triple CHARACTER SET utf8;

DROP USER IF EXISTS triple;

CREATE
USER 'triple'@'localhost' IDENTIFIED BY 'triple';

CREATE
USER 'triple'@'%' IDENTIFIED BY 'triple';

GRANT ALL PRIVILEGES ON triple.* TO
'triple'@'localhost';
GRANT ALL PRIVILEGES ON triple.* TO
'triple'@'%';

use
triple;

CREATE TABLE triple.user
(
    id    varchar(255) NOT NULL,
    point int(11) DEFAULT '0',
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE triple.place
(
    id varchar(255) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE triple.review
(
    id         varchar(255) NOT NULL,
    content    longtext,
    created_at datetime     DEFAULT NULL,
    updated_at datetime     DEFAULT NULL,
    place_id   varchar(255) DEFAULT NULL,
    user_id    varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY        FKn429agmmvh298piqrnnd4gbfg (place_id),
    KEY        FKiyf57dy48lyiftdrf7y87rnxi (user_id),
    CONSTRAINT FKiyf57dy48lyiftdrf7y87rnxi FOREIGN KEY (user_id) REFERENCES triple.user (id),
    CONSTRAINT FKn429agmmvh298piqrnnd4gbfg FOREIGN KEY (place_id) REFERENCES triple.place (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE triple.attached_photo
(
    id        varchar(255) NOT NULL,
    review_id varchar(255) NOT NULL,
    PRIMARY KEY (id),
    KEY       FKjb1h16xhjextj0vgf99cbu22r (review_id),
    CONSTRAINT FKjb1h16xhjextj0vgf99cbu22r FOREIGN KEY (review_id) REFERENCES review (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE triple.point
(
    id         bigint(20) NOT NULL AUTO_INCREMENT,
    amount     int(11) DEFAULT NULL,
    created_at datetime     DEFAULT NULL,
    status     varchar(255) DEFAULT NULL,
    user_id    varchar(255) DEFAULT NULL,
    PRIMARY KEY (id),
    KEY        FKh4qxmn9mig6kith0ish2r67ka (user_id),
    CONSTRAINT FKh4qxmn9mig6kith0ish2r67ka FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;