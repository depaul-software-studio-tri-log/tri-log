alter table runs 
add column shoeid int;

alter table runs
add FOREIGN KEY(shoeid) references shoes(shoeid);