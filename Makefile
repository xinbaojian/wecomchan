clean:
	go clean

run: clean
	bee run -gendoc=true -downdoc=true