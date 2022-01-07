clean:
	go clean

run: clean
	bee run -gendoc=true -downdoc=true

build: clean
	docker build -t registry.cn-hangzhou.aliyuncs.com/xinbj/wecomchan:gov1.1 .

.ONESHELL:
push: build
	docker push registry.cn-hangzhou.aliyuncs.com/xinbj/wecomchan:gov1.1
	docker rmi registry.cn-hangzhou.aliyuncs.com/xinbj/wecomchan:gov1.1
	docker image prune -f

start:
	docker-compose --compatibility up -d

down:
	docker-compose --compatibility down
	docker image prune -f
	docker volume prune -f

restart: down start logs
	echo "restart successfully.."

logs:
	docker-compose --compatibility logs -f --tail 2000