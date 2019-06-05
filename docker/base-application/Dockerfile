
FROM maven:3-jdk-8 as builder

ADD pom.xml /pom.xml

ADD src /src

ADD libs /target/libs

RUN ["mvn", "install"]

FROM openjdk:8

RUN apt-get -q update && \
    apt-get install -q -y --no-install-recommends \
	  xorg \
      unzip \
      wget \
      curl && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

RUN mkdir /mcr-install && \
    mkdir /opt/mcr && \
    cd /mcr-install && \
    wget -q http://ssd.mathworks.com/supportfiles/downloads/R2019a/Release/2/deployment_files/installer/complete/glnxa64/MATLAB_Runtime_R2019a_Update_2_glnxa64.zip && \
    unzip -q MATLAB_Runtime_R2019a_Update_2_glnxa64.zip && \
    rm -f MATLAB_Runtime_R2019a_Update_2_glnxa64.zip && \
    ./install -destinationFolder /opt/mcr -agreeToLicense yes -mode silent && \
    cd / && \
    rm -rf mcr-install


ENV LD_LIBRARY_PATH /opt/mcr/v96/runtime/glnxa64:/opt/mcr/v96/bin/glnxa64:/opt/mcr/v96/sys/os/glnxa64

COPY --from=builder /target /target

COPY MPC_2.mat /target

COPY docker/base-application/entrypoint.sh /target

RUN chmod +x target/entrypoint.sh

ENTRYPOINT ["target/entrypoint.sh"]
