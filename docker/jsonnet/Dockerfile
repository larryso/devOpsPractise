FROM golang:bookworm AS builder

RUN set -eux \
    && apt update && apt install -y \
       ca-certificates \
       git \
       wget \
    && rm -rf /var/lib/apt/lists/*

ENV CGO_ENABLED=0 \
    GOOS=linux

RUN set -eux \
    && git clone https://github.com/google/go-jsonnet \
    && cd go-jsonnet \
    && go env -w GOPROXY=https://goproxy.cn \
    && go build ./cmd/jsonnet

FROM scratch
COPY --from=builder /go/go-jsonnet/jsonnet /

ENTRYPOINT ["/jsonnet"]