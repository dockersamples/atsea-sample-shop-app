c=1
while [[ $c -le 10000 ]]; do
    time curl -H "Content-Type: application/json" -X GET 'http://localhost:8080/api/product/' | python -m json.tool    
    (( c++ ))
done
