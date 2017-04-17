c=1
while [[ $c -le 10000 ]]; do
    time curl  -X GET 'http://localhost:8080/'  
    (( c++ ))
done
