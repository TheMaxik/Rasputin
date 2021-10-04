Rasputin is a tool that allows you to find any publicly available Minecraft server on port 25565 by pining it with a "Minecraft ping".
<br>
<br>
First i tried to use this on my local machine but i got told that it would take forever - and it did.
<br>
So i used Redis made it to work with on multiple instances. Maybe even docker?
<br>
<br>
**Requirements:**
       <br>
       A Server with Mysql and Redis. Redis and Mysql have to be on same machine.
       <br>
       <br>
**Mysql:**
       <br>Nothing special here just a db with a table called "servers". Just import Database.sql in your SqlServer
       <br>
       <br>
**Redis:**
       <br>Here you have to work a bit. Insert all Ip's you want to scan into the redis db.
       <br>I wrote a simple program that increments IPs from 1.1.1.1 to 224.0.0.0 but im not uploading it.
       <br>Insering 4.2 billion Ips takes only about 5 minutes.
       <br>Rasputin will grab random ips from Redis!
       <br>
       <br>
**How to use:**
       <br>1.Build or download Rasputin.jar
       <br>2.Run Rasputin.jar and wait until it crashes.
       <br>3.Edit the config.yml that was created.
       <br>4.Wait until the first Servers come in.
