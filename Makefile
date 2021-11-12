clean:
	go clean

run: clean
	bee run -gendoc=true -downdoc=true

build: clean
	docker build -t xiuyuan/wecomchan:gov1 .

start:
	docker-compose --compatibility up -d

down:
	docker-compose --compatibility down
	docker image prune -f
	docker volume prune -f