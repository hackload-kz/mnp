docker build . -t payment-gateway-integration:latest
docker tag payment-gateway-integration:latest docker.io/infinity2462/payment-gateway-integration:latest
docker push infinity2462/payment-gateway-integration:latest
