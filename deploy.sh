TAG=$(date +"%Y%m%d%H%M%S")
echo "Building docker image: "$TAG
docker build -t ccr.ccs.tencentyun.com/thelook/growth:$TAG .
docker push ccr.ccs.tencentyun.com/thelook/growth:$TAG