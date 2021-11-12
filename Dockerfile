FROM golang:1.17.3-alpine as builder

ENV GO111MODULE on
ENV GOPROXY https://goproxy.io,direct

COPY . /go/src/wecomchan
WORKDIR /go/src/wecomchan

RUN set -eux \
    && sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories \
    && apk add gcc libc-dev \
    && go clean \
    && go build main.go

FROM docker.io/library/alpine:latest
LABEL maintainer="xinbj <baojian.xin.py@gmail.com>"

ADD conf/ /root/conf
RUN mkdir /root/data
WORKDIR /root
# date zone 设置时间，避免相差8个小时
RUN set -eux \
    && sed -i 's/dl-cdn.alpinelinux.org/mirrors.ustc.edu.cn/g' /etc/apk/repositories \
    &&apk update && apk upgrade && apk add ca-certificates && update-ca-certificates \
    && apk add --update tzdata && cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime \
    && echo "Asia/Shanghai" > /etc/timezone \
    && rm -rf /var/cache/apk/*
ENV TZ=Asia/Shanghai
ENV LANG=C.UTF-8

COPY --from=builder /go/src/wecomchan/main /root/main

VOLUME /root/data

CMD ["/root/main"]