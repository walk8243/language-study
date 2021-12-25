# Python

## Pythonのための仮想環境

```sh
# 仮想環境の作成
python -m venv {仮想環境名}

# 起動
# UNIX
. {仮想環境名}/bin/activate
# Windows
.\{仮想環境名}\Scripts\activate

# 停止
deactivate
```

## 依存関係

```sh
# 保存
pip freeze > requirements.txt

# インストール
pip install -r requirements.txt
```
