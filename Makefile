clean:
	go clean

run: clean
	bee run -gendoc=true -downdoc=true

build: clean
	docker build -t xiuyuan/wecomchan:gov1 .

save:
	docker save -o wecomchan.tar xiuyuan/wecomchan:gov1
	tar cvjpf wecomchan.tar.bz2 wecomchan.tar

start:
	docker-compose --compatibility up -d

down:
	docker-compose --compatibility down
	docker image prune -f
	docker volume prune -f