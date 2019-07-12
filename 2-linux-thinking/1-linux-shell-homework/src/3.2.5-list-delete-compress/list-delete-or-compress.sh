#!/bin/bash



keydelete=1
keycompress=2


 


for f in `find /home/minnie/Desktop/test -type f -size +100k` 
do
	echo $f
	read -p "press 1 to delete or 2 to compress this file: " key
	if [ $key -eq $keydelete ]
		then
			
			now=$(date +"%T")
			echo -e "$f $now" >> /home/minnie/Desktop/test/log.txt
			rm -r $f
			echo "delete $f success"
			
		else
			if [ $key -eq $keycompress ]
				then
					zip -r $f.zip /home/minnie/Desktop/test

					echo "compress $f success"
			fi
					
	fi
done

