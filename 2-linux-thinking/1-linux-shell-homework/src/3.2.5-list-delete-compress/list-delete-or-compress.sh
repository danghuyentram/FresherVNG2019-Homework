#!/bin/bash



keydelete=1
keycompress=2


for f in `find /home/cpu11371/Downloads -type f -size +100k` 
do
	echo $f
	read -p "press 1 to delete or 2 to compress this file: " key
	if [ $key -eq $keydelete ]
		then
			
			now=$(date +"%T")
			echo -e "$f $now" >> /home/cpu11371/Downloads/log.txt
			rm -r $f
			echo "delete $f success"
			
		else
			if [ $key -eq $keycompress ]
				then
					zip -r $f.zip /home/cpu11371/Downloads
					echo "compress $f success"
			fi
					
	fi
done

