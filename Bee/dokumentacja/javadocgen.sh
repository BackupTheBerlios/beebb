WINDOWSTITLE="Bee Project"
DESTDIR="../program/dist/javadoc"
ENCODING="UTF-8"
DOCENCODING="UTF-8"
CHARSET="UTF-8"

NETBEANS=`which netbeans`
NETBEANSPATH=""
first=$[1]
pola=""
x=1;
while [ $x -le 20 ]
do
    pola=$pola","$x
    blok=`echo $NETBEANS | cut -d/ -f$pola`
    if [ -d $blok ] 
	then
	    if [ $[x -1] -ge 1 ] 
	    then
		if [ $[first] -gt 0 ] 
		then 
		    NETBEANSPATH=`echo $NETBEANS | cut -d/ -f$[x-1]`
		    first=$[0]
		else
		NETBEANSPATH=$NETBEANSPATH/`echo $NETBEANS | cut -d/ -f$[x-1]`
		fi
	    fi
    fi
x=$[x + 1]
done
JAVADOC=`which javadoc`
LIBS=`find ../program/web/WEB-INF/lib -name "*.jar" -printf :%p`
CLASSPATH=$NETBEANSPATH/ide4/modules/autoload/ext/servlet-api-2.4.jar:$NETBEANSPATH/ide4/modules/autoload/ext/jsp-api-2.0.jar$LIBS


echo "Cleaning target direcotry ..."
rm -r $DESTDIR

$JAVADOC -d $DESTDIR \
	-encoding $ENCODING \
	-docencoding $DOCENCODING \
	-charset $CHARSET \
	-use -splitindex \
	-windowtitle "$WINDOWSTITLE" \
	-classpath $CLASSPATH \
	-sourcepath ../program/src \
	-source 1.5 `find ../program/src/pl/ltd/bee -name "*.java"` `find ../program/src/pl/ltd/bee/Exceptions -name "*.java"`
	
	
