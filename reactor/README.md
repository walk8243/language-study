## Reactor サンプルアプリケーション

Spring Boot + Project Reactor を使ったリアクティブプログラミングの検証用アプリケーションです。

### 主な機能

- `TestController`：Fluxを使った簡単なレスポンスの返却
- `RateLimitController`：ページング情報を含むAPIレスポンス（record型）を返却
- `ZipController`：`CallApiRepository`の処理を並列で複数回呼び出し、Fluxでまとめて返却

### 技術要素

- Spring Boot
- Project Reactor (Flux, Mono)
- REST API

### 実行方法

```sh
./gradlew bootRun
```

### エンドポイント例

- `/test` : 動作確認用エンドポイント
- `/rate-limit` : ページング情報付きレスポンス
- `/parallel` : 並列API呼び出しのサンプル
- `/sequential` : 順次API呼び出しのサンプル
- `/zip` : 2つのAPIレスポンスをまとめて返却
