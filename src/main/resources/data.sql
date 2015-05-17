INSERT INTO `rule` ( `active_rule`, `amount_spent`, `day_time`, `description`, `moltiply_points`, `number_visit`, `rule_type`, `sum_points`) 
VALUES (true, '1', '', 'Receive 1 point every pound spent', '2', '0', 'ONTOTAL', '0');

INSERT INTO `rule` ( `active_rule`, `amount_spent`, `day_time`, `description`, `moltiply_points`, `number_visit`, `rule_type`, `sum_points`) 
VALUES (true, '100', '', 'Receive 5 points if you spend more than 100 pound', '1', '0', 'ONTOTAL', '5');

INSERT INTO `rule` ( `active_rule`, `amount_spent`, `day_time`, `description`, `moltiply_points`, `number_visit`, `rule_type`, `sum_points`) 
VALUES (true, '0', 'WED 18', 'Double points if you buy before 6pm on Wednesday', '2', '0', 'ONDAYANDTIME', '0');

INSERT INTO `rule` ( `active_rule`, `amount_spent`, `day_time`, `description`, `moltiply_points`, `number_visit`, `rule_type`, `sum_points`) 
VALUES (true, '0', 'THU 18', 'Double points if you buy before 6pm on Thursday', '2', '0', 'ONDAYANDTIME', '0');

INSERT INTO `rule` ( `active_rule`, `amount_spent`, `day_time`, `description`, `moltiply_points`, `number_visit`, `rule_type`, `sum_points`) 
VALUES (true, '0', '', 'Receive 5 points every 3rd visit in the month', '1', '3', 'ONVISIT', '5');

