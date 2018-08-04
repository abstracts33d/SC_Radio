Server.default.waitForBoot({
p=ProxySpace.push(s);
p.fadeTime=6;~a.ar;~a.play;

n={|r,f,n=0,d=1|round(r**LFNoise0.ar([4,1,8,2]!d)*f,n)};
v={|f,a=1|LFPulse.ar(f)*a*[1,1.01]};

r = Routine({
	var delta;
	loop {
		delta = rrand(30, 75);
		"Will wait ".post; delta.postln;
		delta.yield;

		~a=[
			{a=Impulse;tanh(a.kr(8).lag*Crackle.ar(LFSaw.kr(3).abs.lag*1.8)+GVerb.ar([a.kr(2)+a.kr(4,0.5)].lag*Blip.ar(4.9,7,0.4)!2,1,1)*5)},

			{HPF.ar(GVerb.ar(({|k|({|i|y=SinOsc;y.ar(i,y.ar(i+k**i)/Decay.kr(Impulse.kr(0.5**i/k),[1,2]+i,k))}!6).sum}!16).sum,1,1)/180,40)},

			{CombC.ar(Mix(SinOsc.ar((1..20)*6.12))* SinOsc.ar([SinOsc.ar(15.4,0,20),SinOsc.ar(1.9,0,37)])* SinOsc.ar([500,400]),1,0.01,10)*0.01},

			{x=LFNoise1.ar(0.5!2);Formlet.ar(Crackle.ar(x.range(1.8,1.98)),TExpRand.ar(200,2e3,x).lag(2),x.range(5e-4,1e-3),0.0012)},

			{Mix.fill(99,{|i| var a=LFNoise2.kr(i*[0.001,0.01]); a*RLPF.ar(Saw.ar(40+(i*10).round(5.73),0.02),(a*2e3).max(100),(i+1)*0.001)})},

			{Resonz.ar(Crackle.ar!2,Duty.kr(Dseq([1,1,4,2,2]/8,inf),0,Dseq([99,Dwhite(99,9e3,1)],inf)),TExpRand.kr(0.001,1,Impulse.kr(8)))*4},

			{a=LFNoise0;b=FBSineC.ar(a.kr(4,2e4,2e4),a.kr(10,16,17),1,1.005,0.7)!2;Latch.ar(b,Impulse.ar(a.kr(0.5,5e3,4e3)))*a.kr(5,0.4,0.5)},

			{Splay.ar(Array.fill(100, { Pulse.ar(LFNoise2.kr(1,2500,10000)) + BPF.ar(Dust.ar(rrand(0.001,0.009)),LFNoise2.kr(0.01,50,5000),0.01,500) }))},

			{Splay.ar({SinOscFB.ar(LFSaw.ar(Sweep.ar(Impulse.ar(Rand()),2)).range(Rand(99,999), Rand()),Sweep.ar(Impulse.ar(Rand())))}!10)},

			{LFCub.ar(LFSaw.kr(LFPulse.kr(1/4,1/4,1/4)*2+2,1,-20,50))+(WhiteNoise.ar(LFPulse.kr(4,0,LFPulse.kr(1,3/4)/4+0.05))/8)!2},

			{a=99;Limiter.ar(FreeVerb.ar(Mix.fill(a, {|i|Saw.ar(55*i, Lag.kr(Impulse.kr(i*0.04), 1.0.rand))}),SinOsc.kr(6), FSinOsc.kr(0.01,0.5)))},

			{a=VarSaw.ar(SinOsc.ar(1/20,7/3,80,80),0,LFNoise1.kr(1,1/2,1/2))*Line.ar(0,1)!2;CombN.ar(a,2,2,20,1,a).softclip},

			{VarSaw.ar((Hasher.ar(Latch.ar(SinOsc.ar((1..4)!2),Impulse.ar([5/2,5])))*300+300).round(60),0,LFNoise2.ar(2,1/3,1/2))/5},

			{BPF.ar(DynKlang.ar(`[[3,5,[4,6]]],Demand.kr(Impulse.kr(1/3),0,Dseq([9,8,6,4],inf))*7).floor,LFPar.ar(1,2,911,999))/3},


			{w=LFSaw;a=w.ar(-3,1)+1/2;f=Sweep.ar(0,3).floor;f=(f**3+f%8+4)*(f%3+3)%49*3;CombN.ar(RLPF.ar(w.ar(f)*a,f**a*30,0.3).tanh,5/6,5/6,6)!2},

			{PitchShift.ar(CombN.ar(Formant.ar(101,4**LFNoise1.kr(0.5)*450,200),1,0.5,99),1,Duty.kr(4,0,Dseq([[6,8,10],[6,7.2,7]]/8,inf))).sum/25!2},

			{Splay.ar(d=n.(3,0.6);Ringz.ar(d*0.01,n.(2,n.(20,400),40,20),d).mean.tanh)},

			{x=Saw.ar([50,50.1]);8.do{|i|f=2**(8-i);x=BRF.ar(AllpassN.ar(x,1,0.1/(12-i),2),80**TRand.ar(0,1,Impulse.ar(f/32,1/2)).lag(1/f)*80,2)};x},

			{v.(v.(100-v.(1/16,20))+v.(2,1+v.(1/4))-0.5*200)+v.(100-v.(1/8,20),v.(8))*0.1},

			{a=LFTri.ar(1);20.do{a=BAllPass.ar(a,80,1);a=((a+0.02)*LFNoise0.kr(1/2)*8).tanh;a=LeakDC.ar(a,0.995)};a*0.1!2},

			{a=PinkNoise.ar(1!2);50.do{a=BBandStop.ar(a,LFNoise1.kr(0.05.rand).exprange(40,15000),exprand(0.1,2))};LPF.ar(a,1e5)},

			{t=[0,3,5,7,10,12]+30;a=Demand.kr(Impulse.kr(8),0,Drand(t+24++t,inf));(BHiPass.ar(LFNoise1.ar(8)**3,[a,a+0.2].midicps,a/2e3,67-a)).tanh},

			{t=[0,0,0,1,5,7,10,12,12,12]+30;a=Duty.kr(1/8,0,Dxrand(t+24++t++t,inf));(BHiPass.ar(LFNoise1.ar(8)**6,[a,a+7].midicps,a/3e3,67-a)).tanh},

			{n=LFNoise0.ar(_);f=[60,61];tanh(BBandPass.ar(max(max(n.(4),l=n.(6)),SinOsc.ar(f*ceil(l*9).lag(0.1))*0.7),f,n.(1).abs/2)*700*l.lag(1))},

			{v=Dust2.ar(4!2);7.do{|n|v=LeakDC.ar(CombL.ar(v,0.2,n*0.001+0.03+LFNoise1.kr(10,0.001),3))};v.clip2(1)},

			{GVerb.ar(Mix.fill(14,{|i|BPF.ar(Decay.ar(Impulse.ar(i*0.1+0.5),0.5,1.2),800*i+40)/(i+1)}),2.7,9).tanh},

			{p=LFPulse;tanh(p.ar(40)*p.kr([0.5,1])+mean({|n|(p.ar(n*2e2+50*p.kr(2-n/[1,3,5],(1..3)/10).sum+4e2)*p.kr(n+1*6,0,0.8).lag)}!2)/2)},

			{p=LFPulse;tanh(p.ar([50,52])*p.kr([2,1]/4)+mean({|n|(p.ar(n*3e2+100*p.kr(2-n/[1,5,7],(0..2)/10).sum+2e2)*p.kr(n+1*6,0,0.8).lag)}!2)/2)},

			{#a,b,c=[LFSaw,TRand,SinOsc];a.ar(c.kr(5),b.kr(-2pi,2pi),b.kr(0.1,1.0,c.kr(340)))*c.ar(680!2*c.kr(b.kr(87,393,c.kr(7))),0,c.kr(pi/13))},

			{LeakDC.ar(GVerb.ar(Mix.fill(14,{|i|BPF.ar(Decay.ar(Impulse.ar(i*0.1+0.5),0.5,1.5),1000*i+40)/(i+1)}),2.7,7)).tanh},

			{z=LFDNoise0;x=Blip.ar(Duty.kr(8,0,Dseq([220,174],inf)).lag,4);5.do{x=BPF.ar(x,z.kr(8!2,400,700).abs,z.kr(0.8!2).range(0.1,1).abs)};x*2},

			{g=LFNoise2.ar(3,11,12);f=LFNoise0.ar(g,4,6).octcps;x=Resonz.ar(LFNoise0.ar(f),f/6,(g**2.3)/f).clip;AllpassC.ar([x,0-x],1,g/230,g/9)},

			{CombC.ar(Klang.ar(`[[100,101,1000,1001]],1,0)*0.1,0.33,LFTri.ar(0.1, 0, 0.1, 0.11)+LFTri.ar(0.17, 0, 0.1, 0.22),10)!2},

			{x=SinOsc;y=LFNoise0;a=y.ar(8);(x.ar(Pulse.ar(1)*24)+x.ar(90+(a*90))+MoogFF.ar(Saw.ar(y.ar(4,333,666)),a*XLine.ar(1,39,99,99,0,2)))!2/3}

		].choose
	}
});

r.next;
TempoClock.default.sched(0, r);

});
