Rasputin is a tool that allows you to find any publicly avaiable Minecraft server on port 25565.

Requirements:
       A Server with Mysql and Redis. Redis and Mysql have to be on same machine.

Mysql:
       Nothing special here just a db with a table called "servers". Just import Database.sql in your SqlServer

Redis:
       Here you have to work a bit. Insert all Ip's you want to scan into the redis db.
       I wrote a simple program that increments IPs from 1.1.1.1 to 224.0.0.0 but im not uploading it.
       
       
How to use:
       1.Build or Download Rasputin.jar
       2.Run Rasputin.jar and wait until it crashes.
       3.Edit the Config.yml that was created.
       4.           Have fun
