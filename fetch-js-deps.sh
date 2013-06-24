#!/bin/sh

set -e

mkdir -vp resources/public/third-party/pagedown

curl -O http://twitter.github.io/bootstrap/assets/bootstrap.zip
curl -O http://codemirror.net/codemirror.zip
curl -O https://pagedown.googlecode.com/hg-history/f2a5240e53a726e3c39b92fab9dd25fd9b7614c4/Markdown.Converter.js

unzip -qu bootstrap.zip -d resources/public/third-party
unzip -qu codemirror.zip -d resources/public/third-party
mv -v Markdown.Converter.js resources/public/third-party/pagedown

rm -v bootstrap.zip
rm -v codemirror.zip

