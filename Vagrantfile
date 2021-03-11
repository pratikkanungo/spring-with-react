Vagrant.configure("2") do |config|
        config.vm.box = "centos/7"
		config.vm.provision "shell", path: "vag-setup.sh"

  config.vm.network "private_network", ip: "127.0.0.1"
      end