#!/bin/sh

set -e

mkdir -vp resources/public/third-party/pagedown
mkdir -vp resources/public/third-party/jquery

curl -O http://twitter.github.io/bootstrap/assets/bootstrap.zip
curl -O http://codemirror.net/codemirror-3.15.zip
curl -O https://pagedown.googlecode.com/hg-history/f2a5240e53a726e3c39b92fab9dd25fd9b7614c4/Markdown.Converter.js
curl -O http://code.jquery.com/jquery-1.10.1.min.js

unzip -qu bootstrap.zip -d resources/public/third-party
unzip -qu codemirror-3.15.zip -d resources/public/third-party
mv -v Markdown.Converter.js resources/public/third-party/pagedown
mv -v jquery-1.10.1.min.js resources/public/third-party/jquery

rm -v bootstrap.zip
rm -v codemirror-3.15.zip

