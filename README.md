# retrofitDemo

  RetrofitManager
                .get("http://ip.taobao.com")
                .create(IPRequest.class)
                .getIPInfo("111.32.23.2")
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<IPResult>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(IPResult ipResult) {
                        textView.setText(ipResult.data.country);
                    }
                });
