# Python

## 参考資料

- [Python.org](https://www.python.org/)
- [プログラミング言語 Python 総合情報サイト - python.jp](https://www.python.jp/)
- [Python入門 ～Pythonのインストール方法やPythonを使ったプログラミングの方法について解説します～ | Let'sプログラミング](https://www.javadrive.jp/python/)

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

## インストール

### Windowsの場合

1. [Microsoft store](https://apps.microsoft.com/store/search?hl=ja-jp&gl=jp&publisher=Python%20Software%20Foundation) でPythonを検索
2. インストールしたいPythonのバージョンをインストール
3. Windowsの検索窓を開く
  - ショートカットキー: Windowsキー + S
4. 「アプリ実行エイリアスの管理」を検索
5. 2でインストールしたバージョンのPythonとそれに付随するpipを有効にする
