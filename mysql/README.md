# mysql

- MySQL
	- データ登録
	- データ参照

## 実行方法

```sh
docker-compose up --detach --force-recreate
```

```sh
mysql -Dstudy -uwalk8243 -pshLG425x
```

## 準備

### Dockerイメージ

```sh
docker build -t language-study-mysql:python -f Dockerfile-python .
docker run language-study-mysql:python
```
