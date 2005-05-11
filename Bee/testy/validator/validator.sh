#!/bin/bash

function val {
if [ $1 != 0 ]; then
	p2=$(echo $2 |  sed -e s#:#%3A#g | sed -e s#/#%2F#g)	
        lynx -dump http://validator.w3.org/check?uri=$p2 | grep "This Page Is Valid" > /dev/null
        zm=$?
        if [ $zm -eq 1 ]; then
                echo "Invalid page $2";
        else
                echo "Valid page $2";
        fi

        for subpage in $(lynx -dump $2| grep ". http://"  | awk '{print $2 } ')
        do
              val `expr $1 - 1 ` $subpage
        done
fi
}  



for page in $(cat pages) 
do
	val $1 $page	
done
